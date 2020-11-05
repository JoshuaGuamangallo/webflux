package com.Joshus.webflux.webflux.api_rest_controller;

import com.Joshus.webflux.webflux.dtos.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;
@ApiTestConfig
class PersonResourceTest {

    @Autowired
    private RestService restService;
    @Test
    void create() {
        PersonDto personDto = new PersonDto((long) 5, "joshua12", "admin", "joshua", Boolean.TRUE, new int[]{5, 8});
        String get = this.restService.restBuilder().post()
                .uri(PersonResource.PERSON).body(BodyInserters.fromObject(personDto))
                .exchange().expectStatus().isCreated().expectBody(String.class).returnResult().getResponseBody();

        assertNotNull(get);
        assertEquals("\"new person\"",get);
    }

    @Test
    void readPerson(){
        PersonDto personDto = this.restService.restBuilder().get()
                .uri(PersonResource.PERSON+PersonResource.ID, 1)
                .exchange().expectStatus().isOk().expectBody(PersonDto.class)
                .returnResult().getResponseBody();

        assertNotNull(personDto);
        assertEquals(1, personDto.getId());
    }

    @Test
    void search(){
        this.restService.restBuilder().get().uri(PersonResource.PERSON).exchange().expectStatus().isOk();
    }

    @Test
    void update(){
        PersonDto personDto = new PersonDto((long)4, "joel11", "user", "joel", Boolean.TRUE, new int[]{5, 8});
        String post=
                this.restService.restBuilder()
                        .put().uri(PersonResource.PERSON+PersonResource.ID,1)
                        .body(BodyInserters.fromObject(personDto))
                        .exchange()
                        .expectStatus().isAccepted()
                        .expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(post);
        assertEquals("\"person edited\"", post);

    }
}