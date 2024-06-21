package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    public Optional<UserEntity> findUserById(Integer id);

    @Query("SELECT u FROM UserEntity u WHERE u.name = :name AND u.email = :email")
    public Optional<List<UserEntity>> findUsersByFilters(String name, String email);

    public Optional<UserEntity>findByEmail(String email);
}
