package com.hamza.howler.util;

import com.hamza.howler.model.Howl;
import com.hamza.howler.model.Like;
import com.hamza.howler.model.User;

public class HowlUtil {
    public static boolean isLikedByRegUser(User user, Howl howl){
        for(Like like:howl.getLikes()){
            if(like.getUser().getId().equals(user.getId())){
                return true;
            }
        }
        return false;
    }

    public static boolean isRetweetedByRegUser(User regUser, Howl howl){
        for (User user :howl.getRetweetUsers()){
            if(user.getId().equals(regUser.getId())){
                return true;
            }
        }
        return false;
    }
}
