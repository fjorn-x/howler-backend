package com.hamza.howler.services;

import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long userId)throws UserException;
    User findUserProfileByJwt(String jwt) throws UserException;
   User updateUser(Long userId,User user)throws UserException;
    User followUser(Long userId,User user) throws UserException;
     List<User> searchUser(String query);

    boolean oldPasswordIsValid(User user, String oldPassword);

    void changePassword(User user, String newPassword);
}
