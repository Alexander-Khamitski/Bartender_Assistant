package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.dto.UserCreateDTO;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.repository.UserRepository;
import com.teachmeskills.bartender_assistant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = "/registration")
    public ModelAndView fillCreateUserForm() {
        return new ModelAndView("user/registrationForm", "userCreateDto", new UserCreateDTO());
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@ModelAttribute UserCreateDTO userCreateDto, Model model) {
        if (!userCreateDto.getUsername().isEmpty() && !userCreateDto.getLogin().isEmpty() && !userCreateDto.getPassword().isEmpty()) {
            service.createUser(userCreateDto);
            String message = String.format("User with '%s' username has been created successfully!", userCreateDto.getUsername());
            model.addAttribute("message", message);
            return new ModelAndView("user/getUserForm", "user", new User());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("user/registrationForm", "userCreateDto", new UserCreateDTO());
    }

    @GetMapping(value = "/get")
    public ModelAndView getUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && userRepository.existsById(id)) {
            User requestedUser = userRepository.findById(id).orElse(null);
            model.addAttribute("user", requestedUser);
            return new ModelAndView("user/getUser");
        }
        return new ModelAndView("user/getUserForm", "user", new User());
    }
}
