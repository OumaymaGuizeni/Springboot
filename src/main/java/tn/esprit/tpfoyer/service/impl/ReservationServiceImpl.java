package tn.esprit.tpfoyer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.*;
import tn.esprit.tpfoyer.repository.*;
import tn.esprit.tpfoyer.service.IReservationService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    ReservationRepository reservationRepository;
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        if (bloc == null || etudiant == null) {
            return null;
        }

        // Find a room in the bloc with available capacity
        for (Chambre chambre : bloc.getChambres()) {
            long currentReservations = (chambre.getReservations() != null)
                    ? chambre.getReservations().stream().filter(Reservation::isEstValide).count()
                    : 0;

            int maxCapacity = 0;
            if (chambre.getTypeC() == TypeChambre.SIMPLE)
                maxCapacity = 1;
            else if (chambre.getTypeC() == TypeChambre.DOUBLE)
                maxCapacity = 2;
            else if (chambre.getTypeC() == TypeChambre.TRIPLE)
                maxCapacity = 3;

            if (currentReservations < maxCapacity) {
                Reservation reservation = new Reservation();
                // format: numChambre-nomBloc-anneeUniversitaire
                String idRes = chambre.getNumeroChambre() + "-" + bloc.getNomBloc() + "-" + LocalDate.now().getYear();
                reservation.setIdReservation(idRes);
                reservation.setAnneeUniversitaire(new Date());
                reservation.setEstValide(true);
                reservation.setChambre(chambre);
                if (reservation.getEtudiants() == null) {
                    reservation.setEtudiants(new java.util.ArrayList<>());
                }
                reservation.getEtudiants().add(etudiant);

                return reservationRepository.save(reservation);
            }
        }
        return null;
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
        if (etudiant != null && etudiant.getReservations() != null) {
            for (Reservation res : etudiant.getReservations()) {
                if (res.isEstValide()) {
                    res.setEstValide(false);
                    // Unassign student (it's @ManyToMany, so we remove from the list)
                    res.getEtudiants().remove(etudiant);
                    // The requirement says "Désaffecter la chambre associée"
                    // But usually, one would just mark it invalid.
                    // If we null it, we lose the link. Let's follow "Désaffecter".
                    res.setChambre(null);
                    return reservationRepository.save(res);
                }
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite,
            String nomUniversite) {
        return reservationRepository.findByAnneeUniversitaireAndNomUniversite(anneeUniversite, nomUniversite);
    }
}
