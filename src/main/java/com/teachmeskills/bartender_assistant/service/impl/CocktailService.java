package com.teachmeskills.bartender_assistant.service.impl;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.Cocktail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CocktailService {

    void createCocktail(Cocktail cocktail);

    Cocktail getCocktail(int id);

    void updateCocktail(Cocktail cocktail);

    void deleteCocktail(int id);

    boolean isCocktailExist(int id);

    boolean isCocktailExist(String name);

    Page<Cocktail> getAllCocktails(Pageable pageable);

    List<Cocktail> getAllCocktails();
}
