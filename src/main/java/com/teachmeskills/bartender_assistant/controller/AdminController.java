package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/get")
    public ModelAndView getUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && userService.isUserExist(id)) {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            return new ModelAndView("user/getUser");
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("user/getUserForm");
    }
}
