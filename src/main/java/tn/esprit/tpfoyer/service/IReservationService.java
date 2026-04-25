package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;
import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservation();

    Reservation updateReservation(Reservation res);

    Reservation retrieveReservation(String idReservation);

    Reservation ajouterReservation(long idBloc, long cinEtudiant);

    Reservation annulerReservation(long cinEtudiant);

    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite);
}
