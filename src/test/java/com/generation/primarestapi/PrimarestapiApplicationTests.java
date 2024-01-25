package com.generation.primarestapi;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.primarestapi.model.entities.Person;
import com.generation.primarestapi.model.repositories.PersonRepository;

@SpringBootTest
class PrimarestapiApplicationTests {

	@Autowired
	PersonRepository repo;
	
	@Test
	void contextLoads() 
	{
		Person p = 	Person
					.builder()
					.name("Stefano")
					.surname("Rubinetti")
					.job("Teacher")
					.dob(LocalDate.of(1995, 8, 27))
					.build();

		repo.save(p);
	}

}
