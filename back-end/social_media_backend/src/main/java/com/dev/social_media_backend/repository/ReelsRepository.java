package com.dev.social_media_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.social_media_backend.model.Reels;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, Long> {
    public List<Reels> findByUserId(Integer userId);
}
