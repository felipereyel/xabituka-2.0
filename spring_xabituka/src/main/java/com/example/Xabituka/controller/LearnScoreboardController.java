package com.example.Xabituka.controller;

import com.example.Xabituka.model.LearnScoreboard;
import com.example.Xabituka.repository.LearnScoreboardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/learn/scoreboard"})
public class LearnScoreboardController {

    private LearnScoreboardRepository repository;

    public LearnScoreboardController(LearnScoreboardRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @PostMapping
    public LearnScoreboard create(@RequestBody LearnScoreboard learnScoreboard){
        return repository.save(learnScoreboard);
    }
}
