package com.teachmeskills.bartender_assistant.service.impl;

import java.util.List;
import java.util.Map;

import com.teachmeskills.bartender_assistant.entity.BartenderRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BartenderRatingService {

    void createBartenderRating(BartenderRating bartenderRating);

    BartenderRating getBartenderRatingById(int id);

    List<BartenderRating> getAllBartenderRatings();

    Map<Integer, Double> getAverageRatings();

    void deleteBartenderRating(int id);

    Page<BartenderRating> getAllBartenderRatings(Pageable paging);
}
