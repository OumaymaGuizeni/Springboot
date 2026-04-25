package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    IReservationService reservationService;

    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservation();
    }

    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        return reservationService.updateReservation(r);
    }

    @PostMapping("/ajouter-reservation/{idBloc}/{cinEtudiant}")
    public Reservation ajouterReservation(@PathVariable("idBloc") long idBloc,
            @PathVariable("cinEtudiant") long cinEtudiant) {
        return reservationService.ajouterReservation(idBloc, cinEtudiant);
    }

    @PutMapping("/annuler-reservation/{cinEtudiant}")
    public Reservation annulerReservation(@PathVariable("cinEtudiant") long cinEtudiant) {
        return reservationService.annulerReservation(cinEtudiant);
    }

    @GetMapping("/get-reservation-par-annee-universitaire-et-nom-universite/{annee}/{nomU}")
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@PathVariable("annee") Date annee,
            @PathVariable("nomU") String nomU) {
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(annee, nomU);
    }
}
