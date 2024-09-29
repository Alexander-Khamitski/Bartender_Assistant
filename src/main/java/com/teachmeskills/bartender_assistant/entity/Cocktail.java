package com.teachmeskills.bartender_assistant.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cocktails")
@EqualsAndHashCode
@ToString
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @NotBlank(message = "Description is required.")
    @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters.")
    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @Getter
    @Setter
    private CocktailStatus status;

    @ToString.Exclude
    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<CocktailIngredient> cocktailIngredients;

    @ToString.Exclude
    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private List<CocktailRating> cocktailRatings;
}
