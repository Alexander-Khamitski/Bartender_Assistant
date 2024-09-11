package com.teachmeskills.bartender_assistant.service.impl;

import com.teachmeskills.bartender_assistant.entity.Cocktail;

public interface CocktailService {

    void createCocktail(Cocktail cocktail);

    Cocktail getCocktail(int id);

    void updateCocktail();

    void deleteCocktail(int id);

    boolean isCocktailExist(int id);
}
