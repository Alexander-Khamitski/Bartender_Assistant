package com.teachmeskills.bartender_assistant.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.teachmeskills.bartender_assistant.entity.CocktailIngredient;
import com.teachmeskills.bartender_assistant.entity.Ingredient;
import com.teachmeskills.bartender_assistant.service.CocktailIngredientsServiceImpl;
import com.teachmeskills.bartender_assistant.service.IngredientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    private CocktailIngredientsServiceImpl cocktailIngredientsService;

    @GetMapping(value = "/create")
    public ModelAndView fillCreateIngredientForm() {
        return new ModelAndView("create/ingredient/createIngredientForm", "ingredient", new Ingredient());
    }

    @PostMapping(value = "/create")
    public ModelAndView createIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient,
                                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("create/ingredient/createIngredientForm", "ingredient", ingredient);
        }
        ingredientService.createIngredient(ingredient);
        String message = String.format("Ingredient '%s' has been created successfully!", ingredient.getName());
        model.addAttribute("message", message);
        return new ModelAndView("create/ingredient/createdIngredient");
    }

    @GetMapping(value = "/get")
    public ModelAndView getIngredient(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Ingredient ingredient = ingredientService.getIngredient(id);
            model.addAttribute("ingredient", ingredient);
            return new ModelAndView("get/ingredient/getIngredient");
        }
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return new ModelAndView("get/ingredient/getIngredientForm", "ingredients", ingredients);
    }

    @GetMapping(value = "/update")
    public ModelAndView fillUpdateIngredientForm(@RequestParam(value = "id", required = false) Integer id,
                                                 Model model) {
        if (id != null) {
            Ingredient ingredient = ingredientService.getIngredient(id);
            model.addAttribute("ingredient", ingredient);
            return new ModelAndView("update/ingredient/updateIngredientForm", "ingredient", ingredient);
        }
        return new ModelAndView("update/ingredient/updateIngredientForm", "ingredient", new Ingredient());
    }

    @PostMapping(value = "/update")
    public ModelAndView updateIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient,
                                         BindingResult result, Model model) {
        int ingredientId = ingredient.getId();
        boolean isIngredientExist = ingredientService.isIngredientExist(ingredientId);
        String message;
        if (result.hasErrors() || !isIngredientExist) {
            if (!isIngredientExist) {
                message = String.format("Ingredient with '%s' ID does not exist. Please, enter valid ingredient id.",
                                        ingredientId);
                model.addAttribute("message", message);
            }
            return new ModelAndView("update/ingredient/updatedIngredient", "ingredient", ingredient);
        }
        ingredientService.updateIngredient(ingredient);
        message = String.format("Ingredient '%s' has been updated successfully!", ingredient.getName());
        return new ModelAndView("update/ingredient/updatedIngredient", "message", message);
    }

    @GetMapping(value = "/delete")
    public ModelAndView fillDeleteIngredientForm() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return new ModelAndView("delete/ingredient/deleteIngredientForm", "ingredients", ingredients);
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteIngredient(@RequestParam(value = "id", required = false) Integer id, Model model) {
        List<CocktailIngredient> cocktailIngredientList =
                cocktailIngredientsService.getAllCocktailIngredientsByIngredientId(id);
        if (!cocktailIngredientList.isEmpty()) {
            Ingredient ingredient = ingredientService.getIngredient(id);
            String cocktailNames = cocktailIngredientList.stream()
                                                         .map(cocktailIngredient -> cocktailIngredient.getCocktail()
                                                                                                      .getName())
                                                         .distinct()
                                                         .collect(Collectors.joining(", "));
            String message = String.format(
                    "Ingredient '%s' is included to next cocktails: '%s'! Deleting is forbidden.", ingredient.getName(),
                    cocktailNames);
            return new ModelAndView("delete/ingredient/deletedIngredient", "message", message);
        }
        ingredientService.deleteIngredient(id);
        String message = String.format("Ingredient with '%s' id has been deleted successfully!", id);
        return new ModelAndView("delete/ingredient/deletedIngredient", "message", message);
    }
}
