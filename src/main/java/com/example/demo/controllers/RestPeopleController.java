package com.example.demo.controllers;


import com.example.demo.entity.PersonEntity;
import com.example.demo.services.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/users")
public class RestPeopleController {
    private final PeopleService peopleServiceImpl;

    @GetMapping()
    public ResponseEntity<List<PersonEntity>> getAllPeople() {
        return ResponseEntity.ok(peopleServiceImpl.getAllPeople());
    }

    @PostMapping()
    public ResponseEntity<Integer> savePerson(@RequestBody @Valid PersonEntity person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(peopleServiceImpl.savePerson(person));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PersonEntity> findPersonById(@PathVariable String userId) {
        PersonEntity person;
        try {
            person = peopleServiceImpl.findPersonById(Integer.parseInt(userId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(person);
    }
}
