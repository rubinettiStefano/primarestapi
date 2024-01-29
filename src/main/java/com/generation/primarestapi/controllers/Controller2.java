package com.generation.primarestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.primarestapi.model.entities.Person;
import com.generation.primarestapi.model.repositories.PersonRepository;

@RestController
public class Controller2 {

    @Autowired
    PersonRepository repo;

    @PutMapping("/prova/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id,@RequestBody Person newVersion) 
    {
        //ci arriva il JSON di una Person, che contiene i valori modificati di quella persona
        //ci arriva tratime path variabile il suo id
        if(repo.findById(id).isPresent())
        {
            newVersion.setId(id);
            return new ResponseEntity<Person>(repo.save(newVersion),HttpStatus.OK);
        }
        else
        {
            //Se sto provando a modicare qualcosa che non esiste
            return new ResponseEntity<String>("Non esiste la persona con id "+id+ ", non posso modificarla",HttpStatus.FORBIDDEN);
        }
    }

}
