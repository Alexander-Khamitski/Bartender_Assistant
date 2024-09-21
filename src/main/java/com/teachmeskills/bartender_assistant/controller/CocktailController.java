package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.service.CocktailServiceImpl;
import com.teachmeskills.bartender_assistant.service.CocktailStatusServiceImpl;
import com.teachmeskills.bartender_assistant.validator.CocktailValidator;
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
@RequestMapping("/cocktail")
public class CocktailController {

    @Autowired
    private CocktailServiceImpl cocktailService;
    @Autowired
    private CocktailStatusServiceImpl cocktailStatusService;

    @GetMapping(value = "/create")
    public ModelAndView fillCreateCocktailForm() {
        return new ModelAndView("create/cocktail/createCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/create")
    public ModelAndView createCocktail(@ModelAttribute Cocktail cocktail, Model model) {
        if (CocktailValidator.isCocktailValid(cocktail)) {
            cocktailService.createCocktail(cocktail);
            String message = String.format("Cocktail '%s' has been created successfully!", cocktail.getName());
            model.addAttribute("message", message);
            return new ModelAndView("create/cocktail/createdCocktail", "cocktail", new Cocktail());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("create/cocktail/createCocktailForm", "cocktail", new Cocktail());
    }

    @GetMapping(value = "/get")
    public ModelAndView getCocktail(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && cocktailService.isCocktailExist(id)) {
            Cocktail cocktail = cocktailService.getCocktail(id);
            model.addAttribute("cocktail", cocktail);
            return new ModelAndView("get/cocktail/getCocktail");
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("get/cocktail/getCocktailForm", "cocktail", new Cocktail());
    }

    @GetMapping(value = "/update")
    public ModelAndView fillUpdateCocktailForm(Model model) {
        model.addAttribute("statuses", cocktailStatusService.getAllStatuses());
        return new ModelAndView("update/cocktail/updateCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/update")
    public ModelAndView updateCocktail(@ModelAttribute Cocktail cocktail, Model model) {
        if (cocktailService.isCocktailExist(cocktail.getId())) {
            cocktailService.updateCocktail(cocktail);
            String message = String.format("Cocktail '%s' has been updated successfully!", cocktail.getName());
            model.addAttribute("message", message);
            return new ModelAndView("update/cocktail/updatedCocktail", "cocktail", new Cocktail());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("update/cocktail/updateCocktailForm", "cocktail", new Cocktail());
    }

    @GetMapping(value = "/delete")
    public ModelAndView fillDeleteCocktailForm() {
        return new ModelAndView("delete/cocktail/deleteCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteStudent(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (cocktailService.isCocktailExist(id)) {
            cocktailService.deleteCocktail(id);
            String message = String.format("Cocktail with '%s' id has been deleted successfully!", id);
            model.addAttribute("message", message);
            return new ModelAndView("delete/cocktail/deletedCocktail", "cocktail", new Cocktail());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("delete/cocktail/deleteCocktailForm", "cocktail", new Cocktail());
    }
}
