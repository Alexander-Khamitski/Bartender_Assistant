package com.teachmeskills.bartender_assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/main")
    public String showHomeForm() {
        return "main/mainPage";
    }
}
