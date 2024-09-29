package com.teachmeskills.bartender_assistant.controller;

import java.util.List;

import com.teachmeskills.bartender_assistant.consts.PaginationConsts;
import com.teachmeskills.bartender_assistant.consts.RatingsConsts;
import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.entity.CocktailRating;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.service.CocktailRatingServiceImpl;
import com.teachmeskills.bartender_assistant.service.CocktailServiceImpl;
import com.teachmeskills.bartender_assistant.service.UserServiceImpl;
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
public class CocktailRatingController {

    @Autowired
    private CocktailRatingServiceImpl cocktailRatingService;
    @Autowired
    private CocktailServiceImpl cocktailService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/ratings")
    public ModelAndView showRatingsPage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable paging = PageRequest.of(page, PaginationConsts.PAGE_SIZE_SEVEN);
        Page<CocktailRating> pageRatings = cocktailRatingService.getAllCocktailRatings(paging);
        List<CocktailRating> cocktailRatings = pageRatings.getContent();
        model.addAttribute("cocktailRatings", cocktailRatings);
        model.addAttribute("currentPage", pageRatings.getNumber());
        model.addAttribute("totalPages", pageRatings.getTotalPages());
        model.addAttribute("totalItems", pageRatings.getTotalElements());
        return new ModelAndView("get/rating/getRatings");
    }

    @GetMapping("/cocktail/rating/create")
    public ModelAndView createCocktailRatingForm(Model model) {
        addRatingAttributes(model);
        return new ModelAndView("create/rating/createRatingForm", "cocktailRating", new CocktailRating());
    }

    @PostMapping(value = "/cocktail/rating/create")
    public ModelAndView updateCocktailIngredient(@Valid @ModelAttribute("cocktailRating") CocktailRating cocktailRating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addRatingAttributes(model);
            return new ModelAndView("create/rating/createRatingForm", "cocktailRating", cocktailRating);
        }
        cocktailRatingService.createCocktailRating(cocktailRating);
        String message = String.format("Rating '%s' for '%s' cocktail has been created successfully!",
                                       cocktailRating.getRating(),
                                       cocktailRating.getCocktail().getName());
        model.addAttribute("message", message);
        return new ModelAndView("create/rating/createdRating", "message", message);
    }

    @PostMapping(value = "/cocktail/rating/delete")
    public ModelAndView deleteCocktailRating(@RequestParam(value = "id") Integer id, Model model) {
        CocktailRating cocktailRating = cocktailRatingService.getCocktailRatingById(id);
        cocktailRatingService.deleteCocktailRating(id);
        String message = String.format("Cocktail rating '%s' for '%s' cocktail has been deleted successfully!",
                                       cocktailRating.getRating(),
                                       cocktailRating.getCocktail().getName());
        model.addAttribute("message", message);
        return new ModelAndView("delete/rating/deletedRating", "cocktailRating", cocktailRating);
    }

    private void addRatingAttributes(Model model) {
        User user = userService.getProfileInfo(model);
        model.addAttribute("user", user);
        List<Cocktail> cocktails = cocktailService.getAllCocktails();
        model.addAttribute("cocktails", cocktails);
        List<Integer> ratings = RatingsConsts.getRatings();
        model.addAttribute("ratings", ratings);
    }
}
