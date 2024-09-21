package com.teachmeskills.bartender_assistant.service.impl;

import java.util.List;

import com.teachmeskills.bartender_assistant.entity.CocktailStatus;

public interface CocktailStatusService {

    CocktailStatus getDefaultCocktailStatus();

    List<CocktailStatus> getAllStatuses();
}
