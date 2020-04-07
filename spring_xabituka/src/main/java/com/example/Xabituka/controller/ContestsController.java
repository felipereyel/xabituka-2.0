package com.example.Xabituka.controller;


import com.example.Xabituka.model.Contests;
import com.example.Xabituka.repository.ContestsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/contests"})
public class ContestsController {

    private ContestsRepository repository;

    public ContestsController(ContestsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();

    }

    @PostMapping
    public Contests create(@RequestBody Contests contest){
        return repository.save(contest);
    }
}
