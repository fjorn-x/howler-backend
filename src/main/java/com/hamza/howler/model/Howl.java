package com.hamza.howler.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Howl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private String content;
    private String image;
    private String video;
    private String location;

    @OneToMany(mappedBy = "howl",cascade = CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @OneToMany
    private List<Howl> replyHowl=new ArrayList<>();

    @ManyToMany
    private List<User> retweetUsers= new ArrayList<>();

    @ManyToOne
    private Howl replyFor;

    private boolean isReply;
    private boolean isHowl;

    private LocalDateTime createdAt;
}
