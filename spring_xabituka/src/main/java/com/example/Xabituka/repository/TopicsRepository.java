package com.example.Xabituka.repository;

import com.example.Xabituka.model.Topics;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {

    List <Topics> findBySubjectId(Long subjectId);
    
}
