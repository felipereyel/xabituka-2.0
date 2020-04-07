package com.example.Xabituka.controller;

import static com.example.Xabituka.model.LearnQuestions.applyHeuristic;
import com.example.Xabituka.model.Topics;
import com.example.Xabituka.repository.LearnAnswersRepository;
import com.example.Xabituka.repository.LearnQuestionsRepository;
import com.example.Xabituka.repository.TopicsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/learn/questions"})
public class LearnQuestionsController {

    private LearnQuestionsRepository learnQuestionsRepository;
    private TopicsRepository topicsRepository;
    private LearnAnswersRepository learnAnswersRepository;

    public LearnQuestionsController(LearnQuestionsRepository learnQuestionsRepository, TopicsRepository topicsRepository, LearnAnswersRepository learnAnswersRepository) {
        this.learnQuestionsRepository = learnQuestionsRepository;
        this.topicsRepository = topicsRepository;
        this.learnAnswersRepository = learnAnswersRepository;
    }

    @GetMapping
    public List findAll() {
        return learnQuestionsRepository.findAll();
    }

//    @GetMapping({"/{id}"})
//    public ResponseEntity findById(@PathVariable long id) {
//        return repository.findById(id)
//                .map(record -> ResponseEntity.ok().body(record))
//                .orElse(ResponseEntity.notFound().build());
//    }
    
    @GetMapping({"/{subjectId}/{userId}"})
    public LearnQuestions findBySubjectId(@PathVariable long subjectId,
                                          @PathVariable long userId){
        List <Long> topicIds = topicsRepository.findBySubjectId(subjectId)
                .stream()
                .map( it -> it.getId())
                .collect(Collectors.toList());
        
        List <LearnQuestions> questions = learnQuestionsRepository.findAll()
                .stream()
                .filter( it -> topicIds.contains(it.getTopicId()))
                .collect(Collectors.toList());
        
        List <Long> learnQuestionIds = questions
                .stream()
                .map( it -> it.getId() )
                .collect(Collectors.toList());
        
        List <LearnAnswers> answers = learnAnswersRepository.findAll()
                .stream()
                .filter( it -> it.getUserId() == userId)
                .filter( it -> learnQuestionIds.contains(it.getLearnQuestionsId()))
                .collect(Collectors.toList());
        
        
        return applyHeuristic(questions, answers);
    }
    
    @PostMapping
    public LearnQuestions create(@RequestBody LearnQuestions learnQuestion){
        return learnQuestionsRepository.save(learnQuestion);
    }
}
