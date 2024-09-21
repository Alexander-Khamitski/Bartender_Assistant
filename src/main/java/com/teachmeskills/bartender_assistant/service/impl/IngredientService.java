package com.teachmeskills.bartender_assistant.service.impl;

import com.teachmeskills.bartender_assistant.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientService {

    void createIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    void updateIngredient(Ingredient ingredient);

    void deleteIngredient(int id);

    boolean isIngredientExist(int id);

    Page<Ingredient> getAllIngredients(Pageable pageable);
}
