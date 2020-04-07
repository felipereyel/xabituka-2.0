package com.example.Xabituka.controller;

import com.example.Xabituka.model.ContestsAnswers;
import com.example.Xabituka.repository.ContestsAnswersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/contests/answers"})
public class ContestsAnswersController {

    private ContestsAnswersRepository repository;

    public ContestsAnswersController(ContestsAnswersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @PostMapping
    public ContestsAnswers create(@RequestBody ContestsAnswers contest){
        return repository.save(contest);
    }
}
