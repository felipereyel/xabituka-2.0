package com.example.Xabituka.repository;

import com.example.Xabituka.model.ContestsQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestsQuestionsRepository extends JpaRepository<ContestsQuestions, Long>{
    
}
