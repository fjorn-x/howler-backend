package com.hamza.howler.dto.mapper;

import com.hamza.howler.dto.HowlDTO;
import com.hamza.howler.dto.LikeDTO;
import com.hamza.howler.dto.UserDTO;
import com.hamza.howler.model.Like;
import com.hamza.howler.model.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDTOMapper {
    public static LikeDTO toLikeDTO(Like like, User regUSer){
        UserDTO userDTO=UserDTOMapper.toUserDTO(like.getUser());
        UserDTO regUserDTO=UserDTOMapper.toUserDTO(regUSer);
        HowlDTO howlDTO=HowlDTOMapper.toHowlDTO(like.getHowl(),regUSer);

        LikeDTO likeDTO=new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setHowl(howlDTO);
        likeDTO.setUser(userDTO);

        return likeDTO;
    }

    public static List<LikeDTO> toLikeDTOS(List<Like> likes, User regUser){
        List<LikeDTO> likeDTOS=new ArrayList<>();
        for (Like like: likes) {
            UserDTO userDTO=UserDTOMapper.toUserDTO(like.getUser());
            HowlDTO howlDTO=HowlDTOMapper.toHowlDTO(like.getHowl(),regUser);
            LikeDTO likeDTO=new LikeDTO();
            likeDTO.setId(like.getId());
            likeDTO.setHowl(howlDTO);
            likeDTO.setUser(userDTO);
            likeDTOS.add(likeDTO);
        }
        return likeDTOS;
    }
}
