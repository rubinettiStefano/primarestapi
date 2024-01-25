package com.generation.primarestapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.primarestapi.model.entities.Person;

public interface PersonRepository extends JpaRepository<Person,Integer>
{

}
