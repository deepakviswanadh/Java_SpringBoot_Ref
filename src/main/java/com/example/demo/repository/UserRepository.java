package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    public UserEntity findUserByName(Integer id);

    @Query("SELECT u FROM UserEntity u WHERE u.name = :name AND u.email = :email")
    public List<UserEntity> findUsersByFilters(String name, String email);
}
