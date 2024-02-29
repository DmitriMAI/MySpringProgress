package com.example.demo.services.impl;

import com.example.demo.exception.PersonExistingEmailException;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.entity.PersonEntity;
import com.example.demo.repository.PeopleRepository;
import com.example.demo.services.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Override
    public List<PersonEntity> getAllPeople() {
        return peopleRepository.findAll();
    }

    /**
     * Сохраняет пользователя в бд
     * @param person
     * @return id пользователя при успехе
     */
    @Override
    @Transactional
    public int savePerson(PersonEntity person) {
        if (peopleRepository
                .findByEmail(person.getEmail()).isEmpty()) {
            return peopleRepository.save(person).getId();
        }
        if (person.getEmail().equals(peopleRepository
                .findByEmail(person.getEmail()).get().getEmail())) {
            throw new PersonExistingEmailException("Person email already exist");
        }
        return peopleRepository.save(person).getId();
    }

    @Override
    public PersonEntity findPersonById(int id) {
        if (!peopleRepository.findById(id).isPresent()) {
            throw new PersonNotFoundException("Person not exist");
        }
        return peopleRepository.findById(id).get();
    }

    /**
     * Обновляет person в таблице
     * @param person
     * @return  Возвращает id пользователя
     */
    @Transactional
    @Override
    public int updatePerson(PersonEntity person) {
        Optional<PersonEntity> newPerson = peopleRepository.findById(person.getId());
        if (newPerson.isPresent()) {
            newPerson.get().setName(person.getName());
            newPerson.get().setEmail(person.getEmail());
            return newPerson.get().getId();
        }
        else {
            throw new PersonNotFoundException("Person not exist");
        }
    }

    @Transactional
    @Override
    public boolean deletePerson(int id){
        Optional<PersonEntity> newPerson = peopleRepository.findById(id);
        if (newPerson.isPresent()) {
            peopleRepository.deleteById(id);
            return true;
        }
        else {
            throw new PersonNotFoundException("Person not exist");
        }
    }
}
