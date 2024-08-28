package com.dev.social_media_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.social_media_backend.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
