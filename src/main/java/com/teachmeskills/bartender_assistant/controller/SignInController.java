package com.teachmeskills.bartender_assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @GetMapping(value = "/signin")
    public ModelAndView fillSignInUserForm() {
        return new ModelAndView("main/signInForm");
    }
}
