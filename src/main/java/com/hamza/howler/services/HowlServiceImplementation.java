package com.hamza.howler.services;

import com.hamza.howler.exceptions.HowlException;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.Howl;
import com.hamza.howler.model.User;
import com.hamza.howler.repository.HowlRepository;
import com.hamza.howler.request.HowlReplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HowlServiceImplementation implements HowlService{

    @Autowired
    private HowlRepository howlRepository;



    @Override
    public Howl createHowl(Howl req, User user) throws UserException {
        Howl howl=new Howl();

        howl.setUser(user);
        howl.setContent(req.getContent());
        howl.setImage(req.getImage());
        howl.setVideo(req.getVideo());
        howl.setCreatedAt(LocalDateTime.now());
        howl.setReply(false);
        howl.setHowl(true);

        return howlRepository.save(howl);
    }

    @Override
    public List<Howl> findAllHowl() {
        return howlRepository.findAllByIsHowlTrueOrderByCreatedAtDesc();
    }

    @Override
    public Howl retweet(Long howlId, User user) throws UserException, HowlException {
        Howl howl=findById(howlId);
        if(howl.getRetweetUsers().contains(user)){
            howl.getRetweetUsers().remove(user);
        }else {
            howl.getRetweetUsers().add(user);
        }
        return howlRepository.save(howl);
    }

    @Override
    public Howl findById(Long howlId) throws HowlException {
        return howlRepository.findById(howlId).orElseThrow();
    }

    @Override
    public void deleteHowlById(Long howlId, Long userId) throws HowlException, UserException {
        Howl howl=findById(howlId);
        if(!userId.equals(howl.getId())){
            throw new UserException("You cannot delete another user's howl");
        }
        howlRepository.delete(howl);
    }

    @Override
    public Howl removeFromRetweet(Long howlId, User user) throws HowlException, UserException {
        return null;
    }

    @Override
    public Howl createReply(HowlReplyRequest req, User user) throws HowlException {
        Howl howl=new Howl();
        Howl replyFor=findById(req.getHowlId());
        howl.setUser(user);
        howl.setReplyFor(replyFor);
        howl.setContent(req.getContent());
        howl.setImage(req.getImage());
        howl.setVideo(req.getVideo());
        howl.setCreatedAt(LocalDateTime.now());
        howl.setReply(true);
        howl.setHowl(false);
        Howl savedReply=howlRepository.save(howl);
//    replyFor.getReplyHowl().add(savedReply);
        howl.getReplyHowl().add((savedReply));
        howlRepository.save(replyFor);

        return replyFor;
    }

    @Override
    public List<Howl> getUserHowls(User user) {
        return howlRepository.findByRetweetUsersContainsOrUser_IdAndIsHowlTrueOrderByCreatedAtDesc(user,user.getId());
    }

    @Override
    public List<Howl> findByLikesContainsUser(User user) {

        return howlRepository.findByLikesUser_id(user.getId());
    }
}
