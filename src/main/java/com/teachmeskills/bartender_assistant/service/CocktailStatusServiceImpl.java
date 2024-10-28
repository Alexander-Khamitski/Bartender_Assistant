package com.teachmeskills.bartender_assistant.service;

import java.util.List;

import com.teachmeskills.bartender_assistant.consts.CocktailStatusIdsConsts;
import com.teachmeskills.bartender_assistant.entity.CocktailStatus;
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
        return cocktailStatusRepository.findById(CocktailStatusIdsConsts.IN_REVIEW)
                                       .orElseThrow(() -> new IllegalArgumentException("Unexpected status id!"));
    }

    public List<CocktailStatus> getAllStatuses() {
        return cocktailStatusRepository.findAll();
    }
}
