package com.teachmeskills.bartender_assistant.repository;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.CocktailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CocktailIngredientsRepository extends JpaRepository<CocktailIngredient, Integer> {

    List<CocktailIngredient> findByCocktailId(int cocktailId);

    List<CocktailIngredient> findByIngredientId(int ingredientId);

    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END " +
            "FROM CocktailIngredient ci " +
            "WHERE ci.cocktail.id = :cocktailId AND ci.ingredient.id = :ingredientId")
    boolean existsByCocktailAndIngredient(@Param("cocktailId") int cocktailId,
                                          @Param("ingredientId") int ingredientId);
}
