package com.teachmeskills.bartender_assistant.service.impl;

import java.util.List;
import java.util.Map;

import com.teachmeskills.bartender_assistant.entity.CocktailRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CocktailRatingService {

    void createCocktailRating(CocktailRating cocktailRating);

    Page<CocktailRating> getAllCocktailRatings(Pageable paging);

    List<CocktailRating> getAllCocktailRatings();

    Map<Integer, Double> getAverageRatings();

    CocktailRating getCocktailRatingById(int id);

    void deleteCocktailRating(int id);
}
