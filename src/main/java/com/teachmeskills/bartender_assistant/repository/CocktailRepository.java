package com.teachmeskills.bartender_assistant.repository;

import com.teachmeskills.bartender_assistant.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {

}
