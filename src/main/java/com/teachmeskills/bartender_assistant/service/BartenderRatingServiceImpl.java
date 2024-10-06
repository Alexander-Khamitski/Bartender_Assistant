package com.teachmeskills.bartender_assistant.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.teachmeskills.bartender_assistant.entity.BartenderRating;
import com.teachmeskills.bartender_assistant.repository.BartenderRatingRepository;
import com.teachmeskills.bartender_assistant.service.impl.BartenderRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BartenderRatingServiceImpl implements BartenderRatingService {

    @Autowired
    private BartenderRatingRepository bartenderRatingRepository;

    @Override
    public void createBartenderRating(BartenderRating bartenderRating) {
        bartenderRatingRepository.save(bartenderRating);
    }

    @Override
    public Page<BartenderRating> getAllBartenderRatings(Pageable paging) {
        return bartenderRatingRepository.findAll(paging);
    }

    @Override
    public BartenderRating getBartenderRatingById(int id) {
        return bartenderRatingRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException(
                                                "Unexpected bartender rating id: " + id));
    }

    @Override
    public List<BartenderRating> getAllBartenderRatings() {
        return bartenderRatingRepository.findAll();
    }

    @Override
    public Map<Integer, Double> getAverageRatings() {
        List<BartenderRating> ratings = getAllBartenderRatings();
        return ratings.stream()
                      .collect(Collectors.groupingBy(
                              BartenderRating::getBartenderId,
                              Collectors.averagingDouble(BartenderRating::getRating)
                      ));
    }

    @Override
    public void deleteBartenderRating(int id) {
        bartenderRatingRepository.deleteById(id);
    }
}
