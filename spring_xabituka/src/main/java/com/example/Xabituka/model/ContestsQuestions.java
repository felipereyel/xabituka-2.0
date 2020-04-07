package com.example.Xabituka.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ContestsQuestions {
    @Id
    @GeneratedValue
    private Long id;
    private String question;
    private Boolean answer;
    @Column(name = "contest_id")
    private Long contestId;
}
