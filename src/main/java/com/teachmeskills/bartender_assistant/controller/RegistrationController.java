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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/registration")
    public ModelAndView fillCreateUserForm() {
        return new ModelAndView("create/user/registrationForm", "userCreateDto", new UserCreateDTO());
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@Valid @ModelAttribute("userCreateDto") UserCreateDTO userCreateDto,
                                   BindingResult result, Model model) {
        String username = userCreateDto.getUsername();
        String login = userCreateDto.getLogin();
        boolean isUserExistByUsername = userService.isUserExistByUsername(username);
        boolean isUserExistByLogin = userService.isUserExistByLogin(login);
        String message;
        if (result.hasErrors() || isUserExistByUsername || isUserExistByLogin) {
            if (isUserExistByUsername) {
                message = String.format("User with username '%s' exists. Duplication is not allowed.", username);
                model.addAttribute("existingUsernameMessage", message);
            }
            if (isUserExistByLogin) {
                message = String.format("User with login '%s' exists. Duplication is not allowed.", login);
                model.addAttribute("existingLoginMessage", message);
            }
            return new ModelAndView("create/user/registrationForm", "userCreateDto", userCreateDto);
        }
        userService.createUser(userCreateDto);
        message = String.format("Welcome to Bartender Assistant, %s!", userCreateDto.getUsername());
        model.addAttribute("message", message);
        return new ModelAndView("main/welcomePage");
    }
}
