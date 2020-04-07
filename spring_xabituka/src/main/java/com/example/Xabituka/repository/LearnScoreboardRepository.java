package com.example.Xabituka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnScoreboardRepository extends JpaRepository<LearnScoreboard, Long> {

}
