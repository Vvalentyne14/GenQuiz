package com.example.BackendApplication2.User;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Customer Role\"")
public class Role {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}
