package com.example.Xabituka.controller;

import com.example.Xabituka.model.Groups;
import com.example.Xabituka.model.Messages;
import com.example.Xabituka.model.User_Group;
import com.example.Xabituka.model.Users;
import com.example.Xabituka.repository.GroupsRepository;
import com.example.Xabituka.repository.MessagesRepository;
import com.example.Xabituka.repository.UserGroupRepository;
import com.example.Xabituka.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/messages"})
public class MessagesController {

    private final MessagesRepository repository;
    private final GroupsRepository groupsRepository;
    private final UserGroupRepository userGroupRepository;
    private final UsersRepository usersRepository;

    public MessagesController(
        MessagesRepository repository,
        GroupsRepository groupsRepository,
        UserGroupRepository userGroupRepository,
        UsersRepository usersRepository
    ) {
        this.repository = repository;
        this.groupsRepository = groupsRepository;
        this.userGroupRepository = userGroupRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping(path = {"/{groupId}"})
    public LinkedHashMap findAll(
            @PathVariable long groupId,
            @RequestHeader("Authorization") String token
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = Optional.ofNullable(usersRepository.findByToken(token));
        if(optUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users user = optUser.get();

        Optional<User_Group> optMembership = userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId);
        if(optMembership.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        User_Group membership = optMembership.get();
        if(membership.getExitedAt() != null){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        res.put("success", true);
        res.put("messages", findMessagesByGroupId(groupId));
        return (LinkedHashMap) res;
    }

    @PostMapping(path = {"/{groupId}"})
    public LinkedHashMap pushNew(
            @PathVariable long groupId,
            @RequestBody LinkedHashMap body,
            @RequestHeader("Authorization") String token
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = Optional.ofNullable(usersRepository.findByToken(token));
        if(optUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users user = optUser.get();

        Optional<User_Group> optMembership = userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId);
        if(optMembership.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        User_Group membership = optMembership.get();
        if(membership.getExitedAt() != null){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        String content = (String) body.get("content");
        if(content.isBlank()){
            res.put("success", false);
            res.put("reason", "Invalid content");
            return (LinkedHashMap) res;
        }

        Messages message = new Messages(
            membership,
            content
        );
        repository.save(message);

        res.put("success", true);
        res.put("messages", findMessagesByGroupId(groupId));
        return (LinkedHashMap) res;
    }

    List<Messages> findMessagesByGroupId(Long groupId){
        List<User_Group> userGroups = userGroupRepository.findByGroupId(groupId);
        return repository.findAllByUserGroupInOrderByCreatedAt(userGroups);
    }
}
