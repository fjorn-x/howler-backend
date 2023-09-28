package com.hamza.howler.repository;

import com.hamza.howler.model.Howl;
import com.hamza.howler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HowlRepository extends JpaRepository<Howl,Long> {

    List<Howl> findAllByIsHowlTrueOrderByCreatedAtDesc();

    List<Howl> findAllByIsReplyTrueOrderByCreatedAtDesc();

    List<Howl> findByRetweetUsersContainsOrUser_IdAndIsHowlTrueOrderByCreatedAtDesc(User user, Long Id);

    List<Howl> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("SELECT h FROM Howl h JOIN h.likes l WHERE l.user.id=:userId")
    List<Howl> findByLikesUser_id(Long userId);

}
