package com.example.demo.controller;

import com.example.demo.model.Personne;
import com.example.demo.service.Annuaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PersonneController {

    @Autowired
    private Annuaire annuaire;

    // GET /personnes
    @GetMapping("personnes")
    public List<Personne> getPersonnes() {
//        ArrayList<Personne> personnes = new ArrayList<>();
//        personnes.add(new Personne("JC", "Dominguez"));
//        personnes.add(new Personne("Marie", "Dupont"));
        return annuaire.getAll();
    }

    // POST /personnes
    @PostMapping("personnes")
    public ResponseEntity<?> addPersonne(@RequestBody Personne newPersonne) {
        if (newPersonne.getNom().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("Le nom est obligatoire");
        }
        else {
            annuaire.add(newPersonne);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPersonne);
        }
    }

    //GET /personnes/4
    @GetMapping("personnes/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Personne> optional = annuaire.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    //DELETE /personnes/4
    @DeleteMapping("personnes/{id}")
    public void delete(@PathVariable Integer id) {
        annuaire.delete(id);
    }

    //PUT /personnes/4
    @PutMapping("personnes/{id}")
    public void update(@RequestBody Personne personne, @PathVariable Integer id) {
        annuaire.update(id, personne);
    }

}
