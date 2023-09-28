package com.hamza.howler.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private UserDTO user;
    private HowlDTO howl;
}
