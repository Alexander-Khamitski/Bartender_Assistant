package com.teachmeskills.bartender_assistant.service;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.CocktailIngredient;
import com.teachmeskills.bartender_assistant.repository.CocktailIngredientsRepository;
import com.teachmeskills.bartender_assistant.service.impl.CocktailIngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailIngredientsServiceImpl implements CocktailIngredientsService {

    @Autowired
    private CocktailIngredientsRepository cocktailIngredientsRepository;

    @Override
    public void createCocktailIngredient(CocktailIngredient cocktailIngredient) {
        cocktailIngredientsRepository.save(cocktailIngredient);
    }

    @Override
    public CocktailIngredient getCocktailIngredient(int id) {
        return cocktailIngredientsRepository.findById(id)
                                            .orElseThrow(
                                                    () -> new IllegalArgumentException("Unexpected recipe id: " + id));
    }

    @Override
    public List<CocktailIngredient> getAllCocktailIngredientsByCocktailId(int id) {
        return cocktailIngredientsRepository.findByCocktailId(id);
    }

    @Override
    public void updateCocktailIngredient(CocktailIngredient cocktailIngredient) {
        cocktailIngredientsRepository.save(cocktailIngredient);
    }

    @Override
    public void deleteCocktailIngredient(CocktailIngredient cocktailIngredient) {
        cocktailIngredientsRepository.delete(cocktailIngredient);
    }

    @Override
    public List<CocktailIngredient> getAllCocktailIngredientsByIngredientId(int ingredientId) {
        return cocktailIngredientsRepository.findByIngredientId(ingredientId);
    }

    @Override
    public boolean isIngredientExistInCocktail(int cocktailId, int ingredientId) {
        return cocktailIngredientsRepository.existsByCocktailAndIngredient(cocktailId, ingredientId);
    }
}
