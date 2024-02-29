package com.example.demo.controllers;

import com.example.demo.entity.UserEntity;
import com.example.demo.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserServiceImpl userService;
    @GetMapping()
    public String registration (Model model){
        model.addAttribute("userEntity", new UserEntity());
        return "registration";
    }

    @PostMapping()
    public String registration(@ModelAttribute("userEntity") @Valid UserEntity userEntity, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.saveUser(userEntity);
        return "redirect:/registration/confirm";
    }
    @GetMapping("/confirm")
    public String confirmRegistration(){
        return "successRegistration";
    }
}
