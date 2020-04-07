package com.example.Xabituka.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/learn/answers"})
public class LearnAnswersController {

    private LearnAnswersRepository repository;

    public LearnAnswersController(LearnAnswersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @PostMapping
    public LearnAnswers create(@RequestBody LearnAnswers learnAnswers){
        return repository.save(learnAnswers);
    }
}
