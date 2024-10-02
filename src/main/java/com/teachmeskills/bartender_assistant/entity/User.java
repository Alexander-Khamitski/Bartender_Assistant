package com.teachmeskills.bartender_assistant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Login is required.")
    @Size(min = 3, max = 50, message = "Login must be between 3 and 50 characters.")
    @Column(name = "login")
    private String login;

    @NotBlank(message = "Password is required.")
    @Size(min = 3, message = "Password must be at least 3 characters long.")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Role is required.")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
