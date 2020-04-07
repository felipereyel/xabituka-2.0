package com.example.Xabituka.controller;

import com.example.Xabituka.model.Subjects;
import com.example.Xabituka.model.Topics;
import com.example.Xabituka.repository.SubjectsRepository;
import com.example.Xabituka.repository.TopicsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/subjects"})
public class SubjectsController {
    private SubjectsRepository repository;

    public SubjectsController(SubjectsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Subjects create(@RequestBody Subjects subject) {
        return repository.save(subject);
    }

}
