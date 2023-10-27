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
     Howl createHowl(Howl req, User user) throws UserException;
     List<Howl> findAllHowl();

     List<Howl> getReplyHowls();

     Howl retweet(Long howlId, User user) throws UserException, HowlException;
     Howl findById(Long howlId) throws HowlException;
     void deleteHowlById(Long howlId,Long userId) throws HowlException, UserException;
//     Howl removeFromRetweet(Long howlId,User user) throws HowlException,UserException;
     Howl createReply(HowlReplyRequest req, User user)throws HowlException;

     List<Howl> getUserHowls(User user);
     List<Howl> findByLikesContainsUser(User user);
}
