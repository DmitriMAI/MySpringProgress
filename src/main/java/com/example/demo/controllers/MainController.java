package com.example.demo.controllers;

import com.example.demo.entity.PersonEntity;
import com.example.demo.exception.PersonExistingEmailException;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.services.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final PeopleService peopleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String getAllPeople(Model model) {
        model.addAttribute("people", peopleService.getAllPeople());
        return "index";
    }

    @GetMapping("/users/new")
    public String createPersonForm(@ModelAttribute("person") PersonEntity person) {
        return "new";
    }

    @PostMapping("/users/new")
    public String createPerson(@ModelAttribute("person") @Valid PersonEntity person,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        try {
            peopleService.savePerson(person);
        } catch (PersonExistingEmailException e){
            model.addAttribute("error", e);
            return "errors";
        }
        return "redirect:/users";
    }

    @GetMapping("users/{id}")
    public String getPersonById(@PathVariable("id") String id, Model model) {
        PersonEntity person;
        try {
            person = peopleService.findPersonById(Integer.parseInt(id));
        } catch (PersonNotFoundException e) {
            model.addAttribute("error", e);
            return "errors";
        }
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("users/{id}/edit")
    public String editPerson(@PathVariable("id") String id, Model model) {
        PersonEntity person;
        try {
            person = peopleService.findPersonById(Integer.parseInt(id));
        } catch (PersonNotFoundException e) {
            model.addAttribute("error", e);
            return "errors";
        }
        model.addAttribute("person", person);
        return "edit";
    }

    @PatchMapping("users/{id}")
    public String update(@ModelAttribute("person") PersonEntity person, Model model) {
        try {
            peopleService.updatePerson(person);
        } catch (PersonNotFoundException e) {
            model.addAttribute("error", e);
            return "errors";
        }
        return "redirect:/users";
    }
    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        try {
            peopleService.deletePerson(id);
        } catch (PersonNotFoundException e) {
            model.addAttribute("error", e);
            return "errors";
        }
        return "redirect:/users";
    }
}
