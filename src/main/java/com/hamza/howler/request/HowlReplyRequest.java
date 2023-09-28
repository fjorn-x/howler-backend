package com.hamza.howler.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HowlReplyRequest {
    private String content;
    private Long howlId;
    private LocalDateTime createdAt;
    private String image;
    private String video;
}
