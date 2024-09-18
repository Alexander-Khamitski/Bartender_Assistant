package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserServiceImpl userService;
    private UserCreateDTO userCreateDto;
    private BindingResult result;
    private Model model;

    @GetMapping
    public ModelAndView showHomeForm() {
        return new ModelAndView("main/mainPage");
    }

    @GetMapping(value = "/registration")
    public ModelAndView fillCreateUserForm() {
        return new ModelAndView("main/registrationForm", "userCreateDto", new UserCreateDTO());
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@Valid @ModelAttribute UserCreateDTO userCreateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("here is errors: ");
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            model.addAttribute("userCreateDto", userCreateDto);
            return new ModelAndView("main/registrationForm", "userCreateDto", userCreateDto);
        }
        userService.createUser(userCreateDto);
        String message = String.format("Welcome to Bartender Assistant, %s!", userCreateDto.getUsername());
        model.addAttribute("message", message);
        return new ModelAndView("main/welcomePage");
    }

    @GetMapping(value = "/signin")
    public ModelAndView fillSignInUserForm() {
        return new ModelAndView("main/signInForm");
    }
}
