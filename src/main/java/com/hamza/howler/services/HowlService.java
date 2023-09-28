package com.hamza.howler.services;

import com.hamza.howler.exceptions.HowlException;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.Howl;
import com.hamza.howler.model.User;
import com.hamza.howler.request.HowlReplyRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HowlService {
    public Howl createHowl(Howl req, User user) throws UserException;
    public List<Howl> findAllHowl();

    public Howl retweet(Long howlId,User user) throws UserException, HowlException;
    public Howl findById(Long howlId) throws HowlException;
    public void deleteHowlById(Long howlId,Long userId) throws HowlException, UserException;
    public Howl removeFromRetweet(Long howlId,User user) throws HowlException,UserException;
    public Howl createReply(HowlReplyRequest req, User user)throws HowlException;

    public List<Howl> getUserHowls(User user);
    public List<Howl> findByLikesContainsUser(User user);
}
