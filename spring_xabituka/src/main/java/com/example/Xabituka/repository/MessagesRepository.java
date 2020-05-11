package com.example.Xabituka.repository;

import com.example.Xabituka.model.Groups;
import com.example.Xabituka.model.Messages;
import com.example.Xabituka.model.User_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    List<Messages> findAllByUserGroupInOrderByCreatedAt(List<User_Group> user_group_ist);
}
