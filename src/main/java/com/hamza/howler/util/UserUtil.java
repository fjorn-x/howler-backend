package com.hamza.howler.util;

import com.hamza.howler.model.User;

public class UserUtil {
    public static final boolean isReqUser(User regUser,User user){
        return regUser.getId().equals(user.getId());
    }
    public static final boolean isFollowedByReqUser(User regUser,User user){
        return regUser.getFollowings().contains(user);
    }
}
