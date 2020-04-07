package com.example.Xabituka.controller;

import com.example.Xabituka.model.Topics;
import com.example.Xabituka.repository.TopicsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/topics"})
public class TopicsController {
    private TopicsRepository repository;

    public TopicsController(TopicsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Topics create(@RequestBody Topics topic) {
        return repository.save(topic);
    }
    
    @GetMapping({"/{subjectId}"})
    public List findBySubjectId(@PathVariable Long subjectId) {
        return repository.findBySubjectId(subjectId);
    }
}
