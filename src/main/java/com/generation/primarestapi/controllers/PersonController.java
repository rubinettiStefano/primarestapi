package com.generation.primarestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.primarestapi.model.entities.Person;
import com.generation.primarestapi.model.repositories.PersonRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
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
    @CrossOrigin
    public Person  insertPerson(@RequestBody Person newPerson) 
    {
        Person salvata= repo.save(newPerson);
        return salvata;
    }
    
}
