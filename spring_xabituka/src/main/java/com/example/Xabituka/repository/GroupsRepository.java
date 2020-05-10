package com.example.Xabituka.repository;

import com.example.Xabituka.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    List<Groups> findByIdIn(List<Long> idList);
}
