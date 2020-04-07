package com.example.Xabituka.repository;

import com.example.Xabituka.model.User_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<User_Group, Long> {
}
