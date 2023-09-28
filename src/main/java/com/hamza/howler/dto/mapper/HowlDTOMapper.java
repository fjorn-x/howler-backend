package com.hamza.howler.dto.mapper;

import com.hamza.howler.dto.HowlDTO;
import com.hamza.howler.dto.UserDTO;
import com.hamza.howler.model.Howl;
import com.hamza.howler.model.User;
import com.hamza.howler.util.HowlUtil;

import java.util.ArrayList;
import java.util.List;

public class HowlDTOMapper {
    public static HowlDTO toHowlDTO(Howl howl, User user){
        HowlDTO howlDTO=new HowlDTO();
        UserDTO userDTO=UserDTOMapper.toUserDTO(howl.getUser());
        boolean isLiked= HowlUtil.isLikedByRegUser(user,howl);
        boolean isRetweeted=HowlUtil.isRetweetedByRegUser(user,howl);
        List<Long> retweetUsersId=new ArrayList<>();
        for (User user1:howl.getRetweetUsers()){
            retweetUsersId.add(user1.getId());
        }
        howlDTO.setId(howl.getId());

        howlDTO.setContent(howl.getContent());
        howlDTO.setCreatedAt(howl.getCreatedAt());
        howlDTO.setImage(howl.getImage());
        howlDTO.setVideo(howl.getVideo());
        howlDTO.setTotalLikes(howl.getLikes().size());
        howlDTO.setTotalReplies(howl.getReplyHowl().size());
        howlDTO.setTotalRetweets(howl.getRetweetUsers().size());
        howlDTO.setUser(userDTO);
        howlDTO.setLiked(isLiked);
        howlDTO.setRetweet(isRetweeted);
        howlDTO.setRetweetUsersId(retweetUsersId);
        howlDTO.setReplyHowls(toHowlDTOS(howl.getReplyHowl(),user));

        return howlDTO;
    }

    public static List<HowlDTO> toHowlDTOS(List<Howl> howls,User reqUser){
        List<HowlDTO> howlDTOS=new ArrayList<>();
        for (Howl howl:howls){
            HowlDTO howlDTO=toReplyHowlDTO(howl,reqUser);
            howlDTOS.add(howlDTO);
        }
        return howlDTOS;
    }

    private static HowlDTO toReplyHowlDTO(Howl howl, User user) {
        HowlDTO howlDTO=new HowlDTO();
        UserDTO userDTO=UserDTOMapper.toUserDTO(howl.getUser());
        boolean isLiked= HowlUtil.isLikedByRegUser(user,howl);
        boolean isRetweeted=HowlUtil.isRetweetedByRegUser(user,howl);
        List<Long> retweetUsersId=new ArrayList<>();
        for (User user1:howl.getRetweetUsers()){
            retweetUsersId.add(user1.getId());
        }
        howlDTO.setId(howl.getId());

        howlDTO.setContent(howl.getContent());
        howlDTO.setCreatedAt(howl.getCreatedAt());
        howlDTO.setImage(howl.getImage());
        howlDTO.setVideo(howl.getVideo());
        howlDTO.setTotalLikes(howl.getLikes().size());
        howlDTO.setTotalReplies(howl.getReplyHowl().size());
        howlDTO.setTotalRetweets(howl.getRetweetUsers().size());
        howlDTO.setUser(userDTO);
        howlDTO.setLiked(isLiked);
        howlDTO.setRetweet(isRetweeted);
        howlDTO.setRetweetUsersId(retweetUsersId);
        return howlDTO;
    }
}
