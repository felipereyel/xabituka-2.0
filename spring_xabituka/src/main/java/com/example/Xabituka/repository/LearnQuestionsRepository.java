package com.example.Xabituka.repository;

import com.example.Xabituka.model.LearnQuestions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnQuestionsRepository extends JpaRepository<LearnQuestions, Long> {
    List <LearnQuestions> findByTopicId(Long topicId);
}
