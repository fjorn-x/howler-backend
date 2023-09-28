package com.hamza.howler.services;

import com.hamza.howler.exceptions.HowlException;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.Howl;
import com.hamza.howler.model.Like;
import com.hamza.howler.model.User;
import com.hamza.howler.repository.HowlRepository;
import com.hamza.howler.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements LikeService{
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private HowlService howlService;
    @Autowired
    private HowlRepository howlRepository;
    @Override
    public Like likeHowl(Long howlId, User user) throws UserException, HowlException {
        Like isLikeExist=likeRepository.isLikeExist(user.getId(), howlId);
        if(isLikeExist!=null){
            likeRepository.delete(isLikeExist);
            return isLikeExist;
        }
        Howl howl=howlService.findById(howlId);
        Like like=new Like();
        like.setHowl(howl);
        like.setUser(user);
        Like savedLike=likeRepository.save(like);
        howl.getLikes().add(savedLike);
        howlRepository.save(howl);
        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long howlId) throws HowlException {
        Howl howl=howlService.findById(howlId);
        return likeRepository.findByHowlId(howlId);
    }
}
