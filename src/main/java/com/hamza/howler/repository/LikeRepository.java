package com.hamza.howler.repository;

import com.hamza.howler.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query("SELECT l FROM Like l WHERE l.user.id=:userId AND l.howl.id=:howlId")
    public Like isLikeExist(@Param("userId") Long userId,@Param("howlId") Long howlId);
    @Query("SELECT l FROM Like l WHERE l.howl.id=:howlId")
    public List<Like> findByHowlId(@Param("howlId") Long howlId);
}
