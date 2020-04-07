package com.example.Xabituka.controller;

import com.example.Xabituka.model.ContestsQuestions;
import com.example.Xabituka.repository.ContestsQuestionsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping({"/contests/questions"})
public class ContestsQuestionsController {
    
    private ContestsQuestionsRepository repository;
    
    public ContestsQuestionsController(ContestsQuestionsRepository repository){
        this.repository = repository;
    }
    
    @GetMapping
    public List findAll(){
        return repository.findAll();
    }
    
    @GetMapping({"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
         return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ContestsQuestions create(@RequestBody ContestsQuestions contestsQuestion){
        return repository.save(contestsQuestion);
    }
    
}
