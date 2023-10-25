package com.hamza.howler.services;

import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.User;
import com.hamza.howler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public User updateUser(Long userId, User req) throws UserException {
        User user=findUserById(userId);
        if(req.getFullName()!=null){
            user.setFullName(req.getFullName());
        }
        if(req.getProfession()!=null){
            user.setProfession(req.getProfession());
        }
        if(req.getProfileImage()!=null){
            user.setProfileImage(req.getProfileImage());
        }
        if(req.getBannerImage()!=null){
            user.setBannerImage(req.getBannerImage());
        }
        if(req.getBirthDate()!=null){
            user.setBirthDate(req.getBirthDate());
        }
        if(req.getWebsite()!=null){
            user.setWebsite(req.getWebsite());
        }
        if(req.getLocation()!=null){
            user.setLocation(req.getLocation());
        }
        if(req.getBio()!=null){
            user.setBio(req.getBio());
        }
        return userRepository.save(user);
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
    @Override
    public boolean oldPasswordIsValid(User user,String oldPassword){
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
    @Override
    public void changePassword(User user,String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
