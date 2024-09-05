package com.teachmeskills.bartender_assistant.controller;

import com.teachmeskills.bartender_assistant.enums.cocktail.CocktailStatus;
import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.repository.CocktailRepository;
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
    private CocktailRepository cocktailRepository;

    @GetMapping(value = "/create")
    public ModelAndView fillCreateCocktailForm() {
        return new ModelAndView("create/createCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/create")
    public ModelAndView createCocktail(@ModelAttribute Cocktail cocktail, Model model) {
        if (!cocktail.getName().isEmpty() && !cocktail.getDescription().isEmpty()) {
            cocktail.setStatus(CocktailStatus.defaultStatus().getStatus());
            System.out.println("Cocktail printed: " + cocktail);
            cocktailRepository.save(cocktail);
            String message = String.format("Cocktail with '%s' id has been created successfully!", cocktail.getId());
            model.addAttribute("message", message);
            return new ModelAndView("create/createCocktail", "cocktail", new Cocktail());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("create/createCocktailForm", "cocktail", new Cocktail());
    }

    @GetMapping(value = "/get")
    public ModelAndView getCocktail(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null && cocktailRepository.existsById(id)) {
            Cocktail requestedCocktail = cocktailRepository.findById(id).orElse(null);
            model.addAttribute("cocktail", requestedCocktail);
            return new ModelAndView("get/getCocktail");
        }
        return new ModelAndView("get/getCocktailForm", "cocktail", new Cocktail());
    }

    @GetMapping(value = "/update")
    public ModelAndView fillUpdateCocktailForm() {
        return new ModelAndView("update/updateCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/update")
    public ModelAndView updateCocktail(@ModelAttribute Cocktail cocktail, Model model) {
        if (cocktailRepository.existsById(cocktail.getId())) {
            cocktailRepository.save(cocktail);
            String message = String.format("Cocktail with '%s' id has been updated successfully!", cocktail.getId());
            model.addAttribute("message", message);
            return new ModelAndView("update/updateCocktail", "cocktail", new Cocktail());
        }
        return new ModelAndView("update/updateCocktailForm", "cocktail", new Cocktail());
    }

    @GetMapping(value = "/delete")
    public ModelAndView fillDeleteCocktailForm() {
        return new ModelAndView("delete/deleteCocktailForm", "cocktail", new Cocktail());
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteStudent(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (cocktailRepository.existsById(id)) {
            cocktailRepository.deleteById(id);
            String message = String.format("Cocktail with '%s' id has been deleted successfully!", id);
            model.addAttribute("message", message);
            return new ModelAndView("create/createCocktail", "cocktail", new Cocktail());
        }
        model.addAttribute("message", "Please, fill in valid data.");
        return new ModelAndView("delete/deleteCocktailForm", "cocktail", new Cocktail());
    }
}
