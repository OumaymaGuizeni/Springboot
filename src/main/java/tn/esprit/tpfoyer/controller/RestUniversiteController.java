package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class RestUniversiteController {

    IUniversiteService universiteService;

    @GetMapping("/retrieve-all-universities")
    public List<Universite> getUniversities() {
        return universiteService.retrieveAllUniversities();
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.updateUniversite(u);
    }

    // Advanced service: affecterFoyerAUniversite
    @PutMapping("/affecter-foyer-a-universite/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable("idFoyer") long idFoyer,
            @PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PutMapping("/desaffecter-foyer-a-universite/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable("idUniversite") long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}
