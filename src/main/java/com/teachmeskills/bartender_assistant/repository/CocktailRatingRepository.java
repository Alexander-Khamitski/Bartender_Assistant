package com.teachmeskills.bartender_assistant.repository;

import java.util.List;
import com.teachmeskills.bartender_assistant.entity.CocktailRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRatingRepository extends JpaRepository<CocktailRating, Integer> {

    List<CocktailRating> findByCocktailId(int cocktailId);
}
