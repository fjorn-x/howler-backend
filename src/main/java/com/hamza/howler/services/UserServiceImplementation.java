package com.hamza.howler.services;

import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.User;
import com.hamza.howler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        return userRepository.findById(userId).orElseThrow(()->new UserException("user not found with id : "+userId));
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmail(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("User not found with email : "+email);
        }
        return user;
    }

    @Override
    public User updateUser(Long userId, User user) throws UserException {
        User existingUser=findUserById(userId);
        if(user.getFullName()!=null){
            existingUser.setFullName(user.getFullName());
        }
        if(user.getProfileImage()!=null){
            existingUser.setProfileImage(user.getProfileImage());
        }
        if(user.getBannerImage()!=null){
            existingUser.setBannerImage(user.getBannerImage());
        }
        if(user.getBirthDate()!=null){
            existingUser.setBirthDate((user.getBirthDate()));
        }
        if(user.getWebsite()!=null){
            existingUser.setWebsite((user.getWebsite()));
        }
        if(user.getLocation()!=null){
            existingUser.setLocation((user.getLocation()));
        }
        if(user.getBio()!=null){
            existingUser.setBio((user.getBio()));
        }

        return userRepository.save(existingUser);
    }

    @Override
    public User followUser(Long userId, User user) throws UserException {
        User followToUser=findUserById(userId);
        if(user.getFollowings().contains(followToUser) && followToUser.getFollowers().contains(user)){
            user.getFollowings().remove(followToUser);
            followToUser.getFollowers().remove(user);
        }else{
            user.getFollowings().add(followToUser);
            followToUser.getFollowers().add(user);
        }
        userRepository.save(followToUser);
        userRepository.save(user);
        return followToUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepository.searchUser(query);
    }
}
