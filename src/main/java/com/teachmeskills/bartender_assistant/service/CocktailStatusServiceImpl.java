package com.teachmeskills.bartender_assistant.service;

import com.teachmeskills.bartender_assistant.entity.CocktailStatus;
import com.teachmeskills.bartender_assistant.enums.cocktail.CocktailStatusEnum;
import com.teachmeskills.bartender_assistant.repository.CocktailStatusRepository;
import com.teachmeskills.bartender_assistant.service.impl.CocktailStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CocktailStatusServiceImpl implements CocktailStatusService {

    @Autowired
    CocktailStatusRepository cocktailStatusRepository;

    @Override
    public CocktailStatus getDefaultCocktailStatus() {
        return cocktailStatusRepository.findById(CocktailStatusEnum.defaultStatus().getId())
                                       .orElseThrow(() -> new IllegalArgumentException("Unexpected status id!"));
    }
}
