package com.example.Xabituka.repository;

import com.example.Xabituka.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByNickname(String nickname);
    List <Users> findByUserType(String userType);
}