package com.hamza.howler.services;

import com.hamza.howler.exceptions.HowlException;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.Like;
import com.hamza.howler.model.User;

import java.util.List;

public interface LikeService {
    public Like likeHowl(Long howlId, User user) throws UserException, HowlException;

    public List<Like> getAllLikes(Long howlId) throws HowlException;
}
