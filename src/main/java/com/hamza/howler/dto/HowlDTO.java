package com.hamza.howler.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class HowlDTO {
     private Long id;
     private String content;
     private String image;
     private String video;
     private  UserDTO user;
     private LocalDateTime createdAt;
     private int totalLikes;
     private int totalReplies;
     private int totalRetweets;
     private boolean isLiked;
     private boolean isRetweet;
     private List<Long> retweetUsersId;
     private List<HowlDTO> replyHowls;
}
