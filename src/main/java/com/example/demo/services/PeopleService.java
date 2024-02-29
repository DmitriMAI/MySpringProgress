package com.example.demo.services;

import com.example.demo.entity.PersonEntity;

import java.util.List;

public interface PeopleService {
    List<PersonEntity> getAllPeople();

    int savePerson(PersonEntity person);

    PersonEntity findPersonById(int id);
    int updatePerson(PersonEntity person);
    boolean deletePerson(int id);
}
