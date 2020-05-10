package com.example.Xabituka.repository;

import com.example.Xabituka.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    List<Groups> findByIdIn(List<Long> idList);
    Optional<Groups> findById(Long id);
}
