package com.teachmeskills.bartender_assistant.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.teachmeskills.bartender_assistant.dto.SignInDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @GetMapping(value = "/signin")
    public ModelAndView fillSignInUserForm(@Valid Model model) {
        model.addAttribute("signInDTO", new SignInDTO());
        return new ModelAndView("main/signInForm");
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@Valid @ModelAttribute("signInDTO") SignInDTO signInDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView ("main/signInForm");
        }
        String message = String.format("Welcome to Bartender Assistant, %s!", signInDTO.getLogin());
        model.addAttribute("message", message);
        return new ModelAndView("main/welcomePage");
    }
}
