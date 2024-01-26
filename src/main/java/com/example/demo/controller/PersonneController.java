package com.example.demo.controller;

import com.example.demo.model.Personne;
import com.example.demo.service.Annuaire;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class PersonneController {

    private Annuaire annuaire = new Annuaire();

    // GET /personnes
    @GetMapping("personnes")
    public List<Personne> getPersonnes(){
//        ArrayList<Personne> personnes = new ArrayList<>();
//        personnes.add(new Personne("JC", "Dominguez"));
//        personnes.add(new Personne("Marie", "Dupont"));
        return annuaire.getAll();
    }

    // POST /personnes
    @PostMapping("personnes")
    public void addPersonne(@RequestBody Personne newPersonne){
        System.out.println(newPersonne);
        annuaire.add(newPersonne);
    }

    //GET /personnes/4
    @GetMapping("personnes/{id}")
    public Personne getById(@PathVariable Integer id){
        return annuaire.findById(id);
    }

    //DELETE /personnes/4
    @DeleteMapping("personnes/{id}")
    public void delete(@PathVariable Integer id){
        annuaire.delete(id);
    }

    //PUT /personnes/4
    @PutMapping("personnes/{id}")
    public void update(@RequestBody Personne personne, @PathVariable Integer id){
        annuaire.update(id, personne);
    }

}
