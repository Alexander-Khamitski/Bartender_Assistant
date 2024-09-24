package com.teachmeskills.bartender_assistant.controller;

import java.util.List;

import com.teachmeskills.bartender_assistant.consts.PaginationConsts;
import com.teachmeskills.bartender_assistant.entity.Ingredient;
import com.teachmeskills.bartender_assistant.service.IngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IngredientsController {

    @Autowired
    private IngredientServiceImpl ingredientService;

    @GetMapping("/ingredients")
    public ModelAndView showCommentPage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable paging = PageRequest.of(page, PaginationConsts.PAGE_SIZE_SEVEN);
        Page<Ingredient> pageComments = ingredientService.getAllIngredients(paging);
        List<Ingredient> ingredients = pageComments.getContent();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("currentPage", pageComments.getNumber());
        model.addAttribute("totalPages", pageComments.getTotalPages());
        model.addAttribute("totalItems", pageComments.getTotalElements());
        return new ModelAndView("get/ingredient/getIngredients");
    }
}
