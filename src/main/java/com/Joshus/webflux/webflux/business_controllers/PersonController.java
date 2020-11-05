package com.Joshus.webflux.webflux.business_controllers;

import com.Joshus.webflux.webflux.documents.Person;
import com.Joshus.webflux.webflux.dtos.PersonDto;
import com.Joshus.webflux.webflux.exceptions.NotFoundException;
import com.Joshus.webflux.webflux.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class PersonController {
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository =personRepository;
    }

    public Mono<ResponseEntity> createPerson(PersonDto personDto){
        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setType(personDto.getType());
        person.setUser(personDto.getUser());
        person.setActive(personDto.getActive());
        person.setData(personDto.getData());
        return this.personRepository.save(person).map(callback ->{
            return new ResponseEntity("\"new person\"", HttpStatus.CREATED);
        }).onErrorReturn(new ResponseEntity("\" person don't created\"", HttpStatus.NOT_ACCEPTABLE));
    }

    public Mono<PersonDto> findPersonById(Long id){
        return this.personRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("person " + id)))
                .map(PersonDto::new);
    }

    public Flux<PersonDto> search(){
        return this.personRepository.findAll().map(PersonDto::new);
    }

    public Mono<ResponseEntity> editPerson(Long id, PersonDto personDto){
        Mono<Person> person = this.personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(" person " + id)))
                .map(personDB -> {
                    personDB.setId(personDto.getId());
                    personDB.setName(personDto.getName());
                    personDB.setType(personDto.getType());
                    personDB.setUser(personDto.getUser());
                    personDB.setActive(personDto.getActive());
                    personDB.setData(personDto.getData());
                    return personDB;
                });
        return Mono.when(person).then(this.personRepository.save(person.block()).map(callback ->{
            return new ResponseEntity("\"person edited\"",HttpStatus.ACCEPTED);
        }));
    }

}
