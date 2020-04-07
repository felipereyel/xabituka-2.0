package com.example.Xabituka.repository;

import com.example.Xabituka.model.LearnAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnAnswersRepository extends JpaRepository<LearnAnswers, Long> {

}
