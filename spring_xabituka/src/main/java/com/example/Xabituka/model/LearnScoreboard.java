package com.example.Xabituka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor // Cria construtores automaticamente
@NoArgsConstructor
@Entity
@Data
public class LearnScoreboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long TopicId;
    private Long totalAnswers;
    private Long totalScore;
}
