package com.hamza.howler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String profileImage;
    private String bannerImage;
    private String bio;
    private String profession;

    @Email
    private String email;
    private String password;
    private String mobile;
    private boolean req_user;
    private boolean login_with_google;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Howl> howl=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @Embedded
    private Verification verification;

    @JsonIgnore
    @ManyToMany
    private List<User> followers=new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<User> followings=new ArrayList<>();
}
