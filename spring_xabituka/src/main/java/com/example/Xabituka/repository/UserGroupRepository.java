package com.example.Xabituka.repository;

import com.example.Xabituka.model.User_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<User_Group, Long> {
    List<User_Group> findByUserId(Long userId);
    List<User_Group> findByGroupId(Long groupId);
    Optional<User_Group> findByUserIdAndGroupId(Long userId, Long groupId);
}
