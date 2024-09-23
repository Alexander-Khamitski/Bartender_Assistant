package com.teachmeskills.bartender_assistant.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.teachmeskills.bartender_assistant.entity.CocktailRating;
import com.teachmeskills.bartender_assistant.repository.CocktailRatingRepository;
import com.teachmeskills.bartender_assistant.service.impl.CocktailRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailRatingServiceImpl implements CocktailRatingService {

    @Autowired
    private CocktailRatingRepository cocktailRatingRepository;

    @Override
    public void createCocktailRating(CocktailRating cocktailRating) {
        cocktailRatingRepository.save(cocktailRating);
    }

    @Override
    public Page<CocktailRating> getAllCocktailRatings(Pageable paging) {
        return cocktailRatingRepository.findAll(paging);
    }

    @Override
    public CocktailRating getCocktailRatingById(int id) {
        return cocktailRatingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected cocktail rating id: " + id));
    }

    @Override
    public List<CocktailRating> getAllCocktailRatings() {
        return cocktailRatingRepository.findAll();
    }

    @Override
    public Map<Integer, Double> getAverageRatings() {
        List<CocktailRating> ratings = getAllCocktailRatings();
        return ratings.stream()
                      .collect(Collectors.groupingBy(
                              rating -> rating.getCocktail().getId(),
                              Collectors.averagingDouble(CocktailRating::getRating)
                      ));
    }

    @Override
    public void deleteCocktailRating(int id) {
        cocktailRatingRepository.deleteById(id);
    }
}
