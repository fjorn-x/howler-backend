package com.hamza.howler.controllers;

import com.hamza.howler.dto.UserDTO;
import com.hamza.howler.dto.mapper.UserDTOMapper;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.User;
import com.hamza.howler.services.UserService;
import com.hamza.howler.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String jwt)throws UserException{
        User user= userService.findUserProfileByJwt(jwt);
        UserDTO userDTO= UserDTOMapper.toUserDTO(user);
        userDTO.setReqUser(true);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId, @RequestHeader("Authorization") String jwt)throws UserException{
        User reqUser= userService.findUserProfileByJwt(jwt);
        User user=userService.findUserById(userId);
        UserDTO userDTO= UserDTOMapper.toUserDTO(user);
        userDTO.setReqUser(UserUtil.isReqUser(reqUser,user));
        userDTO.setFollowed(UserUtil.isFollowedByReqUser(reqUser,user));
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUser(@RequestParam String query, @RequestHeader("Authorization") String jwt)throws UserException{
        User reqUser= userService.findUserProfileByJwt(jwt);
        List<User> user=userService.searchUser(query);
        List<UserDTO> userDTOS= UserDTOMapper.toUserDTOS(user);
        return new ResponseEntity<>(userDTOS, HttpStatus.ACCEPTED);
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User req, @RequestHeader("Authorization") String jwt)throws UserException{
        User reqUser= userService.findUserProfileByJwt(jwt);
        User user=userService.updateUser(reqUser.getId(),req);
        UserDTO userDTO= UserDTOMapper.toUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<UserDTO> followUser(@PathVariable Long userId, @RequestHeader("Authorization") String jwt)throws UserException{
        User reqUser= userService.findUserProfileByJwt(jwt);
        User user=userService.followUser(userId,reqUser);
        UserDTO userDTO= UserDTOMapper.toUserDTO(user);
        userDTO.setFollowed(UserUtil.isFollowedByReqUser(reqUser,user));
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
}
