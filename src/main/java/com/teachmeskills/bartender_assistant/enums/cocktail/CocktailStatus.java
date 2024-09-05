package com.teachmeskills.bartender_assistant.enums.cocktail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CocktailStatus {

    IN_REVIEW("In review"), APPROVED("Approved"), DECLINED("Declined");

    private String status;

    public static CocktailStatus defaultStatus() {
        return IN_REVIEW;
    }
}
