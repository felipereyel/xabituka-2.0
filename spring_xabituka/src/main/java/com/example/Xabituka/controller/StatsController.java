package com.example.Xabituka.controller;

import com.example.Xabituka.model.*;
import com.example.Xabituka.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Xabituka.model.LearnQuestions.JoinQuestions;
import static com.example.Xabituka.model.LearnQuestions.applyHeuristic;

@RequestMapping({"/stats"})
@RestController
public class StatsController {
    private LearnQuestionsRepository learnQuestionsRepository;
    private TopicsRepository topicsRepository;
    private LearnAnswersRepository learnAnswersRepository;
    private SubjectsRepository subjectsRepository;
    private UsersRepository usersRepository;


    public StatsController(
            LearnQuestionsRepository learnQuestionsRepository,
            TopicsRepository topicsRepository,
            LearnAnswersRepository learnAnswersRepository,
            SubjectsRepository subjectsRepository,
            UsersRepository usersRepository
    ) {
        this.learnQuestionsRepository = learnQuestionsRepository;
        this.topicsRepository = topicsRepository;
        this.learnAnswersRepository = learnAnswersRepository;
        this.subjectsRepository = subjectsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping({"/{subjectId}/{userId}"})
    public LinkedHashMap findBySubjectId(@PathVariable long subjectId, @PathVariable long userId) {

        return getUserStatsBySubjectId(userId, subjectId);
    }

    private LinkedHashMap getUserStatsBySubjectId(long userId, Long subjectId) {
        LinkedHashMap subjectStats = new LinkedHashMap();
        List<Subjects> subjects = subjectsRepository.findAllById(Collections.singleton(subjectId));

        if(subjects.isEmpty()){
            return subjectStats;
        }

        Subjects subject = subjects.get(0);

        List<LinkedHashMap> topics = new ArrayList<>();
        int numberOfAnswers = 0;
        int numberOfCorrectAnswers = 0;

        //calcular stats por topico
        List<Topics> topicsModel = topicsRepository.findBySubjectId(subject.getId());
        for (Topics topicModel : topicsModel) {
            int numberOfTopicAnswers = 0;
            int numberOfCorrectTopicAnswers = 0;

            List<LearnQuestions> topicQuestions = learnQuestionsRepository.findByTopicId(topicModel.getId());

            List<Long> topicQuestionsIds = topicQuestions
                    .stream()
                    .map(it -> it.getId())
                    .collect(Collectors.toList());

            List<LearnAnswers> topicAnswers = learnAnswersRepository.findAll()
                    .stream()
                    .filter(it -> it.getUserId() == userId)
                    .filter(it -> topicQuestionsIds.contains(it.getLearnQuestionsId()))
                    .collect(Collectors.toList());

            final List<LinkedHashMap> joinado = JoinQuestions(topicQuestions, topicAnswers);

            for(LinkedHashMap joinadinho: joinado){
                if(joinadinho.containsKey("answer"))
                {
                    numberOfTopicAnswers++;
                    numberOfAnswers++;
                    if((Boolean) joinadinho.get("answer"))
                    {
                        numberOfCorrectTopicAnswers++;
                        numberOfCorrectAnswers++;
                    }
                }
            }

            if(numberOfTopicAnswers == 0) continue;

            LinkedHashMap topicStats = new LinkedHashMap();
            topicStats.put("topicId", topicModel.getId());
            topicStats.put("topicName", topicModel.getName());
            topicStats.put("numberOfTopicAnswers", numberOfTopicAnswers);
            topicStats.put("numberOfCorrectTopicAnswers", numberOfCorrectTopicAnswers);
            topics.add(topicStats);
        }

        subjectStats.put("subjectId", subject.getId());
        subjectStats.put("subjectName", subject.getName());
        subjectStats.put("topics", topics);
        subjectStats.put("numberOfAnswers", numberOfAnswers);
        subjectStats.put("numberOfCorrectAnswers", numberOfCorrectAnswers);

        return subjectStats;
    }
}



