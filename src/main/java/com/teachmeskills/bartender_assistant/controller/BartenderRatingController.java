package com.teachmeskills.bartender_assistant.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.teachmeskills.bartender_assistant.consts.PaginationConsts;
import com.teachmeskills.bartender_assistant.consts.RatingsConsts;
import com.teachmeskills.bartender_assistant.entity.BartenderRating;
import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.service.BartenderRatingServiceImpl;
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
public class BartenderRatingController {

    @Autowired
    private BartenderRatingServiceImpl bartenderRatingService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/ratings/bartenders")
    public ModelAndView showRatingsPage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable paging = PageRequest.of(page, PaginationConsts.PAGE_SIZE_SEVEN);
        Page<BartenderRating> pageRatings = bartenderRatingService.getAllBartenderRatings(paging);
        List<BartenderRating> bartenderRatings = pageRatings.getContent();
        Map<Integer, String> bartenderNames = bartenderRatings.stream()
                                                              .collect(Collectors.toMap(
                                                                      BartenderRating::getBartenderId,
                                                                      r -> userService.getUser(r.getBartenderId())
                                                                                      .getUsername(),
                                                                      (existing, replacement) -> existing
                                                              ));
        model.addAttribute("bartenderRatings", bartenderRatings);
        model.addAttribute("bartenderNames", bartenderNames);
        model.addAttribute("currentPage", pageRatings.getNumber());
        model.addAttribute("totalPages", pageRatings.getTotalPages());
        model.addAttribute("totalItems", pageRatings.getTotalElements());
        return new ModelAndView("get/rating/bartender/getRatings");
    }

    @GetMapping("/bartender/rating/create")
    public ModelAndView createBartenderRatingForm(Model model) {
        addRatingAttributes(model);
        return new ModelAndView("create/rating/bartender/createRatingForm", "bartenderRating", new BartenderRating());
    }

    @PostMapping(value = "/bartender/rating/create")
    public ModelAndView createBartenderRatingForm(@Valid @ModelAttribute("bartenderRating") BartenderRating bartenderRating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addRatingAttributes(model);
            return new ModelAndView("create/rating/bartender/createRatingForm", "bartenderRating", bartenderRating);
        }
        bartenderRatingService.createBartenderRating(bartenderRating);
        String message = String.format("Rating '%s' for bartender '%s' has been added successfully!",
                                       bartenderRating.getRating(),
                                       userService.getUserDto(bartenderRating.getBartenderId()).getUsername());
        model.addAttribute("message", message);
        return new ModelAndView("create/rating/bartender/createdRating", "message", message);
    }

    @PostMapping(value = "/bartender/rating/delete")
    public ModelAndView deleteBartenderRating(@RequestParam(value = "id") Integer id, Model model) {
        BartenderRating bartenderRating = bartenderRatingService.getBartenderRatingById(id);
        bartenderRatingService.deleteBartenderRating(id);
        String message = String.format("Rating '%s' for '%s' bartender has been deleted successfully!",
                                       bartenderRating.getRating(),
                                       userService.getUserDto(bartenderRating.getBartenderId()).getUsername());
        model.addAttribute("message", message);
        return new ModelAndView("delete/rating/bartender/deletedRating", "bartenderRating", bartenderRating);
    }

    private void addRatingAttributes(Model model) {
        User user = userService.getProfileInfo(model);
        model.addAttribute("user", user);
        List<User> bartenders = userService.getBartenders();
        model.addAttribute("bartenders", bartenders);
        List<Integer> ratings = RatingsConsts.getRatings();
        model.addAttribute("ratings", ratings);
    }
}
