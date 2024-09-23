package com.teachmeskills.bartender_assistant.consts;

import java.util.List;

public interface RatingsConsts {

    int ONE = 1;
    int TWO = 2;
    int THREE = 3;
    int FOUR = 4;
    int FIVE = 5;

    static List<Integer> getRatings() {
        return List.of(ONE, TWO, THREE, FOUR, FIVE);
    }
}
