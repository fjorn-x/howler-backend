package com.hamza.howler.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String profileImage;
    private String profession;
    private String location;
    private String birthDate;
    private String website;
    private String mobile;
    private String bannerImage;
    private String bio;
    private boolean req_user;
    private boolean login_with_google;
    private List<UserDTO> followers=new ArrayList<>();
    private List<UserDTO> followings=new ArrayList<>();
    private boolean followed;
    private boolean isVerified;
}
