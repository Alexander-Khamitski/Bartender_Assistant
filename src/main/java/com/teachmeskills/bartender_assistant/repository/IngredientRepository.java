package com.teachmeskills.bartender_assistant.repository;

import com.teachmeskills.bartender_assistant.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    boolean existsByName(String name);
}
