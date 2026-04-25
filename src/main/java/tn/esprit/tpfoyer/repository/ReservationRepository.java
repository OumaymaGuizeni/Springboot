package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entity.Reservation;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByEstValide(boolean estValide);

    List<Reservation> findByChambre_IdChambre(long idChambre);

    @Query("SELECT r FROM Reservation r WHERE r.anneeUniversitaire = :annee " +
            "AND r.chambre.bloc.foyer.universite.nomUniversite = :nomU")
    List<Reservation> findByAnneeUniversitaireAndNomUniversite(@Param("annee") Date annee, @Param("nomU") String nomU);
}