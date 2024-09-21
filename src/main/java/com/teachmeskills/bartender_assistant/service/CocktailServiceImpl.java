package com.teachmeskills.bartender_assistant.service;

import com.teachmeskills.bartender_assistant.entity.Cocktail;
import com.teachmeskills.bartender_assistant.repository.CocktailRepository;
import com.teachmeskills.bartender_assistant.service.impl.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private CocktailStatusServiceImpl cocktailStatusService;

    @Override
    public void createCocktail(Cocktail cocktail) {
        cocktail.setStatus(cocktailStatusService.getDefaultCocktailStatus());
        cocktailRepository.save(cocktail);
    }

    @Override
    public Cocktail getCocktail(int id) {
        return cocktailRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("Unexpected cocktail id!"));
    }

    @Override
    public void updateCocktail(Cocktail cocktail) {
        cocktailRepository.save(cocktail);
    }

    @Override
    public void deleteCocktail(int id) {
        cocktailRepository.deleteById(id);
    }

    @Override
    public boolean isCocktailExist(int id) {
        return cocktailRepository.existsById(id);
    }

    public Page<Cocktail> getAllCocktails(Pageable pageable) {
        return cocktailRepository.findAll(pageable);
    }
}
