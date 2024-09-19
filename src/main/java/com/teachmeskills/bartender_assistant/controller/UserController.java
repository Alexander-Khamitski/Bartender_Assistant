package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/admin/user/get")
    public ModelAndView getUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && userService.isUserExist(id)) {
            UserDTO userDto = userService.getUserDto(id);
            model.addAttribute("userDto", userDto);
            return new ModelAndView("get/user/getUser");
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("get/user/getUserForm");
    }

    @GetMapping(value = "/admin/user/update")
    public ModelAndView updateUser() {
        return new ModelAndView("update/user/updateUserForm", "user", new User());
    }


    @PostMapping("/admin/user/update")
    public ModelAndView createUser(@ModelAttribute User user, Model model) {
        if (userService.isUserExist(user.getId())) {
            userService.updateUser(user);
            String message = String.format("User with '%s' ID and '%s' username has been updated.", user.getId(), user.getUsername());
            model.addAttribute("message", message);
            return new ModelAndView("update/user/updatedUserPage");
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("update/user/updateUserForm");
    }

    @GetMapping(value = "/admin/user/delete")
    public ModelAndView deleteUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && userService.isUserExist(id)) {
            UserDTO userDto = userService.getUserDto(id);
            userService.deleteUser(id);
            String message = String.format("User with '%s' ID and '%s' username has been deleted.", userDto.getId(), userDto.getUsername());
            model.addAttribute("message", message);
            return new ModelAndView("delete/user/deletedUserPage");
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("delete/user/deleteUserForm");
    }
}
