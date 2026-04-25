package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {

    IChambreService chambreService;

    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        return chambreService.retrieveAllChambres();
    }

    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId) {
        return chambreService.retrieveChambre(chambreId);
    }

    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        return chambreService.addChambre(c);
    }

    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        return chambreService.updateChambre(c);
    }

    @GetMapping("/get-chambres-par-nom-universite/{nomUniversite}")
    public List<Chambre> getChambresParNomUniversite(@PathVariable("nomUniversite") String nomUniversite) {
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }

    @GetMapping("/get-chambres-par-bloc-et-type/{idBloc}/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable("idBloc") long idBloc,
            @PathVariable("typeC") TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }

    @GetMapping("/get-chambres-non-reserve-par-nom-universite-et-type-chambre/{nomU}/{type}")
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@PathVariable("nomU") String nomU,
            @PathVariable("type") TypeChambre type) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomU, type);
    }

    @GetMapping("/get-chambres-non-reserve-toutes-universites")
    public List<Chambre> getChambresNonReserveToutesUniversites() {
        return chambreService.getChambresNonReserveToutesUniversites();
    }
}
