package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.entity.Ingredient;
import com.teachmeskills.bartender_assistant.service.IngredientServiceImpl;
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
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientServiceImpl ingredientService;

    @GetMapping(value = "/create")
    public ModelAndView fillCreateIngredientForm() {
        return new ModelAndView("create/ingredient/createIngredientForm", "ingredient", new Ingredient());
    }

    @PostMapping(value = "/create")
    public ModelAndView createIngredient(@ModelAttribute Ingredient ingredient, Model model) {
        if (!ingredient.getName().isEmpty()) {
            ingredientService.createIngredient(ingredient);
            String message = String.format("Ingredient '%s' has been created successfully!", ingredient.getName());
            model.addAttribute("message", message);
            return new ModelAndView("create/ingredient/createdIngredient", "ingredient", new Ingredient());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("create/ingredient/createIngredientForm", "ingredient", new Ingredient());
    }

    @GetMapping(value = "/get")
    public ModelAndView getIngredient(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && ingredientService.isIngredientExist(id)) {
            Ingredient ingredient = ingredientService.getIngredient(id);
            model.addAttribute("ingredient", ingredient);
            return new ModelAndView("get/ingredient/getIngredient");
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("get/ingredient/getIngredientForm", "ingredient", new Ingredient());
    }

    @GetMapping(value = "/update")
    public ModelAndView fillUpdateIngredientForm() {
        return new ModelAndView("update/ingredient/updateIngredientForm", "ingredient", new Ingredient());
    }

    @PostMapping(value = "/update")
    public ModelAndView updateIngredient(@ModelAttribute Ingredient ingredient, Model model) {
        if (ingredientService.isIngredientExist(ingredient.getId())) {
            ingredientService.updateIngredient(ingredient);
            String message = String.format("Ingredient '%s' has been updated successfully!", ingredient.getName());
            model.addAttribute("message", message);
            return new ModelAndView("update/ingredient/updatedIngredient", "ingredient", new Ingredient());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("update/ingredient/updateIngredientForm", "ingredient", new Ingredient());
    }

    @GetMapping(value = "/delete")
    public ModelAndView fillDeleteIngredientForm() {
        return new ModelAndView("delete/ingredient/deleteIngredientForm", "ingredient", new Ingredient());
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteIngredient(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (ingredientService.isIngredientExist(id)) {
            ingredientService.deleteIngredient(id);
            String message = String.format("Ingredient with '%s' id has been deleted successfully!", id);
            model.addAttribute("message", message);
            return new ModelAndView("delete/ingredient/deletedIngredient", "ingredient", new Ingredient());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("delete/ingredient/deleteIngredientForm", "ingredient", new Ingredient());
    }
}
