package com.dev.social_media_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.social_media_backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);

    public User findUserById(Integer userId);

    @Query("select u from User u where u.firstName LIKE %:query% or u.lastName LIKE %:query% or u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
