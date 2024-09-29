package com.teachmeskills.bartender_assistant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cocktail_ratings")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CocktailRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Cocktail is required.")
    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @NotNull(message = "Rating is required.")
    @Column(name = "rating")
    private Integer rating;
}
