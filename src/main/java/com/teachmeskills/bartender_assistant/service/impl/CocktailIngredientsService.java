package com.teachmeskills.bartender_assistant.service.impl;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.CocktailIngredient;

public interface CocktailIngredientsService {

    void createCocktailIngredient(CocktailIngredient cocktailIngredient);

    CocktailIngredient getCocktailIngredient(int id);

    List<CocktailIngredient> getAllCocktailIngredientsByCocktailId(int id);

    void updateCocktailIngredient(CocktailIngredient cocktailIngredient);

    void deleteCocktailIngredient(CocktailIngredient cocktailIngredient);
}
