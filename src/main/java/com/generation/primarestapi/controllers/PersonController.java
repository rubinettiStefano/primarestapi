package com.generation.primarestapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    
    @GetMapping("/people/{id}")
    //Una response entity è un oggetto composto da Body + Status Code
    //Il ? si legge ANY, mi va bene restituire una response entity con QUALSIASI
    //COSA nel BODY
    public ResponseEntity<?> getPerson(@PathVariable Integer id) 
    {
       Optional<Person> op = repo.findById(id);
       //Un optional di person è un contenitore che può contenere una persona
       //così come essere vuoto

       //isPresent()   isEmpty()
       //se ho trovato la persona con quell'id
       //isPresent() darà true e isEmpty() false
       //se non la ho trovata
       //isPresent() darà false e isEmpty() true

       if(op.isPresent())
       {
            Person p = op.get();//ESTRAIAMO LA PERSONA DALL'OPTIONAL
            //chiamare get() su un Optional vuoto risulta in una
            //NoSuchElementException 

            //Il primo parametro è il body, l'oggetto da JSONIZZARE
            //il secondo è lo status CODE
            return new ResponseEntity<Person>(p,HttpStatus.OK );
       }
       else
       {
        //fai altro
            return new ResponseEntity<String>("Non esiste la persona con id "+id,HttpStatus.NOT_FOUND );
       }

    }

    //ID NON VALIDO, no persona con quell'id nel DB
    //Problema di conversione da JSON a Person
    //Person non valida (NOT POSSIBLE)
    @PutMapping("/people/{id}")
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

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id)//void manda indietro una request senza body
    {
        if(repo.findById(id).isPresent())
        {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            //Se sto provando a cancellare qualcosa che non esiste
            return new ResponseEntity<String>("Non esiste la persona con id "+id+ ", non posso cancellarla",HttpStatus.BAD_REQUEST);
        }
    }

  
}
