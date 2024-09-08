package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/registration")
    public ModelAndView fillCreateUserForm() {
        return new ModelAndView("user/registrationForm", "user", new User());
    }

    @PostMapping(value = "/registration")
    public ModelAndView createUser(@ModelAttribute User user, Model model) {
        if (!user.getUsername().isEmpty() && !user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
            user.setRoleId(2);
            userRepository.save(user);
            String message = String.format("User with '%s' id has been created successfully!", user.getId());
            model.addAttribute("message", message);
            return new ModelAndView("user/getUserForm", "user", new User());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("user/registrationForm", "user", new User());
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
