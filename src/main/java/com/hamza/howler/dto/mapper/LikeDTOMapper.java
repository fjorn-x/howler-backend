package com.hamza.howler.dto.mapper;

import com.hamza.howler.dto.HowlDTO;
import com.hamza.howler.dto.LikeDTO;
import com.hamza.howler.dto.UserDTO;
import com.hamza.howler.model.Like;
import com.hamza.howler.model.User;

public class LikeDTOMapper {
    public static LikeDTO toLikeDTO(Like like, User regUSer){
        LikeDTO likeDTO=new LikeDTO();
        UserDTO userDTO=UserDTOMapper.toUserDTO(like.getUser());
        UserDTO regUserDTO=UserDTOMapper.toUserDTO(regUSer);
        HowlDTO howlDTO=HowlDTOMapper.toHowlDTO(like.getHowl(),regUSer);
        likeDTO.setId(like.getId());
        likeDTO.setHowl(howlDTO);
        likeDTO.setUser(userDTO);

        return likeDTO;
    }
}
