package com.hamza.howler.model;

import jakarta.persistence.*;

@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Howl howl;
}
