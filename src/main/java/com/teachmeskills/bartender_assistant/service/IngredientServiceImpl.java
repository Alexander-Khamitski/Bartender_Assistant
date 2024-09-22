package com.teachmeskills.bartender_assistant.service;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.Ingredient;
import com.teachmeskills.bartender_assistant.repository.IngredientRepository;
import com.teachmeskills.bartender_assistant.service.impl.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void createIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredientRepository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Unexpected ingredient id!"));
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(int id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public boolean isIngredientExist(int id) {
        return ingredientRepository.existsById(id);
    }

    @Override
    public Page<Ingredient> getAllIngredients(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
