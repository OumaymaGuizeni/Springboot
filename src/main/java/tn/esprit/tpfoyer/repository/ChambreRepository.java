package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByTypeC(TypeChambre typeC);

    List<Chambre> findByBloc_IdBloc(long idBloc);

    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite")
    List<Chambre> findByUniversite_NomUniversite(@Param("nomUniversite") String nomUniversite);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite " +
            "AND c.typeC = :type " +
            "AND c NOT IN (SELECT r.chambre FROM Reservation r WHERE r.anneeUniversitaire = CURRENT_DATE)")
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@Param("nomUniversite") String nomUniversite,
            @Param("type") TypeChambre type);

    @Query("SELECT c FROM Chambre c WHERE c NOT IN (SELECT r.chambre FROM Reservation r WHERE r.anneeUniversitaire = CURRENT_DATE)")
    List<Chambre> getChambresNonReserveToutesUniversites();
}