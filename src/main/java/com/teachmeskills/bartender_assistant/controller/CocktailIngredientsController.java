package com.teachmeskills.bartender_assistant.controller;

import java.util.List;

import com.teachmeskills.bartender_assistant.consts.PaginationConsts;
import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.entity.CocktailIngredient;
import com.teachmeskills.bartender_assistant.entity.Ingredient;
import com.teachmeskills.bartender_assistant.service.CocktailIngredientsServiceImpl;
import com.teachmeskills.bartender_assistant.service.CocktailServiceImpl;
import com.teachmeskills.bartender_assistant.service.IngredientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CocktailIngredientsController {

    @Autowired
    private CocktailIngredientsServiceImpl cocktailIngredientsService;
    @Autowired
    private CocktailServiceImpl cocktailService;
    @Autowired
    private IngredientServiceImpl ingredientService;

    @GetMapping("/recipes")
    public ModelAndView showRecipesPage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable paging = PageRequest.of(page, PaginationConsts.PAGE_SIZE_SEVEN);
        Page<Cocktail> pageComments = cocktailService.getAllCocktails(paging);
        List<Cocktail> cocktails = pageComments.getContent();
        model.addAttribute("cocktails", cocktails);
        model.addAttribute("currentPage", pageComments.getNumber());
        model.addAttribute("totalPages", pageComments.getTotalPages());
        model.addAttribute("totalItems", pageComments.getTotalElements());
        return new ModelAndView("get/recipe/getRecipes");
    }

    @GetMapping("/recipe/get")
    public ModelAndView getRecipe(@RequestParam(value = "id") Integer id, Model model) {
        Cocktail cocktail = cocktailService.getCocktail(id);
        model.addAttribute("cocktail", cocktail);
        List<CocktailIngredient> cocktailIngredients = cocktailIngredientsService.getAllCocktailIngredientsByCocktailId(
                id);
        model.addAttribute("cocktailIngredients", cocktailIngredients);
        return new ModelAndView("get/recipe/getRecipe");
    }

    @GetMapping("/cocktail/ingredient/update")
    public ModelAndView updateCocktailIngredientForm(@RequestParam(value = "id") Integer id, Model model) {
        CocktailIngredient cocktailIngredient = cocktailIngredientsService.getCocktailIngredient(id);
        model.addAttribute("cocktailIngredient", cocktailIngredient);
        return new ModelAndView("update/recipe/updateCocktailIngredientForm");
    }

    @PostMapping(value = "/cocktail/ingredient/update")
    public ModelAndView updateCocktailIngredient(@Valid @ModelAttribute("cocktailIngredient") CocktailIngredient cocktailIngredient,
                                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("update/recipe/updateCocktailIngredientForm", "cocktailIngredient",
                                    cocktailIngredient);
        }
        cocktailIngredientsService.updateCocktailIngredient(cocktailIngredient);
        String message = String.format("Cocktail ingredient '%s' in '%s' cocktail has been updated successfully!",
                                       cocktailIngredient.getIngredient().getName(),
                                       cocktailIngredient.getCocktail().getName());
        model.addAttribute("message", message);
        return new ModelAndView("update/recipe/updatedCocktailIngredient", "cocktailIngredient", cocktailIngredient);
    }

    @PostMapping(value = "/cocktail/ingredient/delete")
    public ModelAndView deleteCocktailIngredient(@RequestParam(value = "id") Integer id, Model model) {
        CocktailIngredient cocktailIngredient = cocktailIngredientsService.getCocktailIngredient(id);
        cocktailIngredientsService.deleteCocktailIngredient(cocktailIngredient);
        String message = String.format("Cocktail ingredient '%s' in '%s' cocktail has been deleted successfully!",
                                       cocktailIngredient.getIngredient().getName(),
                                       cocktailIngredient.getCocktail().getName());
        model.addAttribute("message", message);
        return new ModelAndView("update/recipe/updatedCocktailIngredient", "cocktailIngredient", cocktailIngredient);
    }

    @GetMapping(value = "/cocktail/ingredient/add")
    public ModelAndView addCocktailIngredient(@RequestParam(value = "id") Integer id, Model model) {
        Cocktail cocktail = cocktailService.getCocktail(id);
        model.addAttribute("cocktail", cocktail);
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.addAttribute("ingredients", ingredients);
        return new ModelAndView("create/recipe/addCocktailIngredient", "cocktailIngredient", new CocktailIngredient());
    }

    @PostMapping(value = "/cocktail/ingredient/add")
    public ModelAndView addCocktailIngredient(@Valid @ModelAttribute("cocktailIngredient") CocktailIngredient cocktailIngredient, BindingResult result, Model model) {
        boolean isCocktailIngredientExist = cocktailIngredientsService.isIngredientExistInCocktail(
                cocktailIngredient.getCocktail().getId(), cocktailIngredient.getIngredient().getId());
        String message;
        if (result.hasErrors() || isCocktailIngredientExist) {
            if (isCocktailIngredientExist) {
                message = String.format("'%s' has been already included into cocktail. Duplication is not allowed.",
                                        cocktailIngredient.getIngredient().getName());
                model.addAttribute("message", message);
            }
            Cocktail cocktail = cocktailService.getCocktail(cocktailIngredient.getCocktail().getId());
            model.addAttribute("cocktail", cocktail);
            List<Ingredient> ingredients = ingredientService.getAllIngredients();
            model.addAttribute("ingredients", ingredients);
            return new ModelAndView("create/recipe/addCocktailIngredient", "cocktailIngredient", cocktailIngredient);
        }
        cocktailIngredientsService.createCocktailIngredient(cocktailIngredient);
        message = String.format("Cocktail ingredient '%s' in '%s' cocktail has been added successfully!",
                                cocktailIngredient.getIngredient().getName(),
                                cocktailIngredient.getCocktail().getName());
        model.addAttribute("message", message);
        return new ModelAndView("update/recipe/updatedCocktailIngredient", "cocktailIngredient", cocktailIngredient);
    }
}
