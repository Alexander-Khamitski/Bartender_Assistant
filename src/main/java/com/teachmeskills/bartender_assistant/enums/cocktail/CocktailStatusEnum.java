package com.teachmeskills.bartender_assistant.enums.cocktail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CocktailStatusEnum {

    IN_REVIEW(1, "In review"), APPROVED(2, "Approved"), DECLINED(3, "Declined");

    private int id;
    private String status;

    public static CocktailStatusEnum defaultStatus() {
        return IN_REVIEW;
    }
}
