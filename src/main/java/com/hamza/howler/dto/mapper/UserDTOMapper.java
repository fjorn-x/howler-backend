package com.hamza.howler.dto.mapper;

import com.hamza.howler.dto.UserDTO;
import com.hamza.howler.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOMapper {

    public static UserDTO toUserDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setProfession(user.getProfession());

        userDTO.setProfileImage(user.getProfileImage());
        userDTO.setBannerImage(user.getBannerImage());
        userDTO.setBio(user.getBio());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setFollowers(toUserDTOS(user.getFollowers()));
        userDTO.setFollowings(toUserDTOS(user.getFollowings()));
        userDTO.setLoginWithGoogle(user.isLoginWithGoogle());
        userDTO.setLocation(user.getLocation());
//        userDTO.setVerified(false);
        return userDTO;
    }

    public static List<UserDTO> toUserDTOS(List<User> followers) {
        List<UserDTO> userDTOS=new ArrayList<>();
        for(User user : followers){
            UserDTO userDTO=new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFullName(user.getFullName());
            userDTO.setProfileImage(user.getProfileImage());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
