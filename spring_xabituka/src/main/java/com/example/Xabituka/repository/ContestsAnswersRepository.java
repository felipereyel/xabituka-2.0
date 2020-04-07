package com.example.Xabituka.repository;

import com.example.Xabituka.model.ContestsAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestsAnswersRepository extends JpaRepository<ContestsAnswers, Long> {

}
