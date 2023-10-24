package com.hamza.howler.controllers;

import com.hamza.howler.dto.LikeDTO;
import com.hamza.howler.dto.mapper.LikeDTOMapper;
import com.hamza.howler.exceptions.HowlException;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.Like;
import com.hamza.howler.model.User;
import com.hamza.howler.services.LikeService;
import com.hamza.howler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;


    @PostMapping("/{howlId}/likes")
    public ResponseEntity<LikeDTO> likeHowl(@PathVariable Long howlId, @RequestHeader("Authorization") String jwt)throws UserException, HowlException {
        User user= userService.findUserProfileByJwt(jwt);
        Like like=likeService.likeHowl(howlId,user);
        LikeDTO likeDTO= LikeDTOMapper.toLikeDTO(like,user);
        return new ResponseEntity<>(likeDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{howlId}")
    public ResponseEntity<List<LikeDTO>> getAllLikes(@PathVariable Long howlId, @RequestHeader("Authorization") String jwt)throws UserException, HowlException {
        User user= userService.findUserProfileByJwt(jwt);
        List<Like> likes=likeService.getAllLikes(howlId);
        List<LikeDTO> likeDTOS= LikeDTOMapper.toLikeDTOS(likes,user);
        return new ResponseEntity<>(likeDTOS, HttpStatus.OK);
    }
}
