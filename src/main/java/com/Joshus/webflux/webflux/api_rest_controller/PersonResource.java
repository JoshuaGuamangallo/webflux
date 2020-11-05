package com.Joshus.webflux.webflux.api_rest_controller;

import com.Joshus.webflux.webflux.business_controllers.PersonController;
import com.Joshus.webflux.webflux.dtos.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(PersonResource.PERSON)
public class PersonResource {
    public static final String PERSON = "/person";
    public static final String ID = "/{id}";

    private PersonController personController;

    @Autowired
    public PersonResource(PersonController personController) {
        this.personController = personController;
    }

    @PostMapping(produces = {"application/json"})
    public Mono<ResponseEntity> create(@RequestBody PersonDto personDto){
        return this.personController.createPerson(personDto);
    }

    @GetMapping(value = ID)
    public Mono<PersonDto> readPerson(@PathVariable Long id){
        return this.personController.findPersonById(id);
    }

    @GetMapping
    public Flux<PersonDto> search(){
        return this.personController.search();
    }

    @PutMapping(value = ID)
    public Mono<ResponseEntity> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        return this.personController.editPerson(id,personDto);
    }

}
