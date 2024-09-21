package com.teachmeskills.bartender_assistant.controller;

import java.util.List;

import com.teachmeskills.bartender_assistant.consts.PaginationConsts;
import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.service.CocktailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private CocktailServiceImpl cocktailService;

    @GetMapping
    public ModelAndView showHomeForm() {
        return new ModelAndView("main/mainPage");
    }

    @GetMapping("/cocktails")
    public ModelAndView showCommentPage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable paging = PageRequest.of(page, PaginationConsts.PAGE_SIZE);
        Page<Cocktail> pageComments = cocktailService.getAllCocktails(paging);
        List<Cocktail> cocktails = pageComments.getContent();
        model.addAttribute("cocktails", cocktails);
        model.addAttribute("currentPage", pageComments.getNumber());
        model.addAttribute("totalPages", pageComments.getTotalPages());
        model.addAttribute("totalItems", pageComments.getTotalElements());
        return new ModelAndView("get/cocktail/getCocktails");
    }
}
