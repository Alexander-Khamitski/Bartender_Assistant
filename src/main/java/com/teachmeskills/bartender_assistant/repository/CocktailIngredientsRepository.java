package com.teachmeskills.bartender_assistant.repository;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.CocktailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailIngredientsRepository extends JpaRepository<CocktailIngredient, Integer> {

    List<CocktailIngredient> findByCocktailId(int cocktailId);
}
