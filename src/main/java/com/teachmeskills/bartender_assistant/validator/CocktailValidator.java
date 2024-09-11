package com.teachmeskills.bartender_assistant.validator;

import com.teachmeskills.bartender_assistant.entity.Cocktail;

public class CocktailValidator {

    public static boolean isCocktailValid(Cocktail cocktail) {
        return !cocktail.getName().isEmpty() && !cocktail.getDescription().isEmpty();
    }
}
