package com.generation.primarestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.primarestapi.model.entities.Person;
import com.generation.primarestapi.model.repositories.PersonRepository;






@RestController
@CrossOrigin
public class PersonController 
{
    @Autowired
    PersonRepository repo;
    //PRODUCE JSON
    //JavaScript Object Notation

    //in automatico nei RestController il return viene CONVERTITO
    //in un JSON
    @GetMapping("/people")
    public List<Person>  getAllPeople() 
    {
        List<Person> people = repo.findAll();
        return people;
    }
    //inserimento è POST
    //La request contiene un JSON, il json della persona da inserire
    //con RequestBody Spring lo prende, lo traduce in un oggetto Person
    //chiamo newPerson
    //@RequestBody è per RestController quello che @ModelAttribute è per il controller
    //normale 
    @PostMapping("/people")
   
    public Person  insertPerson(@RequestBody Person newPerson) 
    {
        Person salvata= repo.save(newPerson);
        return salvata;
    }
    
    @GetMapping("/people/{id}")//{id} che è un segnaposto, in automatico quello che c'è dopo lo / viene messo nella variabile id
    //localhost:8080/people/6    -> spring vede che dove c'è il placeholder {id} ora c'è 6 e lo mette nel parametro id
    public Person getPerson(@PathVariable Integer id) 
    {
        return repo.findById(id).get();
    }

    @PutMapping("/people/{id}")
    public Person updatePerson(@PathVariable Integer id,@RequestBody Person newVersion) 
    {
        //ci arriva il JSON di una Person, che contiene i valori modificati di quella persona
        //ci arriva tratime path variabile il suo id
        newVersion.setId(id);
        return repo.save(newVersion);
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Integer id)//void manda indietro una request senza body
    {
        repo.deleteById(id);
    }
    
}
