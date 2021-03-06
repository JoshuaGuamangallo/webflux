package com.Joshus.webflux.webflux.dtos;

import com.Joshus.webflux.webflux.documents.Person;

public class PersonDto {
    private Long id;
    private String name, type, user;
    private Boolean active;
    private int[] data;

    public PersonDto() {
    }

    public PersonDto(Long id, String name, String type, String user, Boolean active, int[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
        this.active = active;
        this.data = data;
    }

    public PersonDto(Person person){
        this(
                person.getId(),
                person.getName(),
                person.getType(),
                person.getUser(),
                person.getActive(),
                person.getData()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
