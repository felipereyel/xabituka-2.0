package com.example.Xabituka.controller;

import com.example.Xabituka.model.Messages;
import com.example.Xabituka.model.User_Group;
import com.example.Xabituka.model.Users;
import com.example.Xabituka.model.Groups;
import com.example.Xabituka.repository.GroupsRepository;
import com.example.Xabituka.repository.MessagesRepository;
import com.example.Xabituka.repository.UserGroupRepository;
import com.example.Xabituka.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/groups"})
public class GroupsController {

    private final GroupsRepository repository;
    private final MessagesRepository messagesRepository;
    private final UserGroupRepository userGroupRepository;
    private final UsersRepository usersRepository;

    public GroupsController(
        GroupsRepository repository,
        MessagesRepository messagesRepository,
        UserGroupRepository userGroupRepository,
        UsersRepository usersRepository
    ) {
        this.repository = repository;
        this.messagesRepository = messagesRepository;
        this.userGroupRepository = userGroupRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public LinkedHashMap findAll(@RequestHeader("Authorization") String token) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = Optional.ofNullable(usersRepository.findByToken(token));

        if(optUser.isEmpty()){
            res.put("sucess", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }

        Users user = optUser.get();

        Set<Groups> allowedGroups = userGroupRepository
                .findByUserId(user.getId())
                .stream()
                .filter(it -> it.getExitedAt() == null)
                .map(it -> it.getGroup())
                .collect(Collectors.toSet());

        List<LinkedHashMap> groups = allowedGroups
                .stream()
                .map(it-> it.toMapWithContext(findLastByGroup(it)))
                .collect(Collectors.toList());


        res.put("sucess", true);
        res.put("groups", groups);
        return (LinkedHashMap) res;
    }

    Messages findLastByGroup(Groups group){
        List<User_Group> userGroups = userGroupRepository.findByGroupId(group.getId());
        List<Messages> messagesList = messagesRepository.findAllByUserGroupInOrderByCreatedAt(userGroups);
        return messagesList.get(0);
    }

//    @GetMapping(path = {"/{id}"})
//    public ResponseEntity findById(@PathVariable long id, @RequestParam String token) {
//        Long userId = usersRepository.findByToken(token).getId();
//        Optional<Groups> group = repository.findById(id);
//
//        if(group.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//
//        Optional<User_Group> membership = Optional.ofNullable(userGroupRepository.findByUserIdAndGroupId(userId, id));
//
//        if(membership.isPresent() && membership.getExitedAt().isNull()){
//            return repository.findById(id)
//                    .map(record -> ResponseEntity.ok().body(record))
//                    .orElse(ResponseEntity.notFound().build());
//        }
//
//    }
}
