package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.service.CocktailServiceImpl;
import com.teachmeskills.bartender_assistant.service.CocktailStatusServiceImpl;
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
    public ModelAndView createCocktail(@Valid @ModelAttribute("cocktail") Cocktail cocktail, BindingResult result,
                                       Model model) {
        String cocktailName = cocktail.getName();
        String message;
        boolean isCocktailExist = cocktailService.isCocktailExist(cocktailName);
        if (result.hasErrors() || isCocktailExist) {
            if (isCocktailExist) {
                message = String.format("Cocktail '%s' exist. Duplication is not allowed.", cocktailName);
                model.addAttribute("message", message);
            }
            return new ModelAndView("create/cocktail/createCocktailForm", "cocktail", cocktail);
        }
        cocktailService.createCocktail(cocktail);
        message = String.format("Cocktail '%s' has been added for review!", cocktailName);
        model.addAttribute("message", message);
        return new ModelAndView("create/cocktail/createdCocktail");
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
    public ModelAndView fillUpdateCocktailForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("statuses", cocktailStatusService.getAllStatuses());
        if (id != null) {
            return new ModelAndView("update/cocktail/updateCocktailForm", "cocktail", cocktailService.getCocktail(id));
        }
        return new ModelAndView("update/cocktail/updateCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/update")
    public ModelAndView updateCocktail(@ModelAttribute Cocktail cocktail, Model model) {
        int cocktailId = cocktail.getId();
        if (cocktailService.isCocktailExist(cocktailId)) {
            Cocktail existingCocktail = cocktailService.getCocktail(cocktailId);
            existingCocktail.setName(cocktail.getName());
            existingCocktail.setDescription(cocktail.getDescription());
            existingCocktail.setStatus(cocktail.getStatus());
            cocktailService.updateCocktail(existingCocktail);
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
    public ModelAndView deleteCocktail(@RequestParam(value = "id", required = false) Integer id, Model model) {
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
