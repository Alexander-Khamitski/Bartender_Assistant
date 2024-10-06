package com.teachmeskills.bartender_assistant.controller;

import java.util.List;
import java.util.Map;

import com.teachmeskills.bartender_assistant.consts.PaginationConsts;
import com.teachmeskills.bartender_assistant.dto.UserDTO;
import com.teachmeskills.bartender_assistant.service.BartenderRatingServiceImpl;
import com.teachmeskills.bartender_assistant.service.UserServiceImpl;
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
public class BartendersController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BartenderRatingServiceImpl bartenderRatingService;

    @GetMapping("/main/bartenders")
    public ModelAndView showCommentPage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable paging = PageRequest.of(page, PaginationConsts.PAGE_SIZE_SEVEN);
        Page<UserDTO> pageComments = userService.getBartenders(paging);
        List<UserDTO> bartenders = pageComments.getContent();
        model.addAttribute("bartenders", bartenders);
        model.addAttribute("currentPage", pageComments.getNumber());
        model.addAttribute("totalPages", pageComments.getTotalPages());
        model.addAttribute("totalItems", pageComments.getTotalElements());
        Map<Integer, Double> averageRatings = bartenderRatingService.getAverageRatings();
        model.addAttribute("averageRatings", averageRatings);
        return new ModelAndView("get/bartender/getBartenders");
    }
}
