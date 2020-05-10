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

        Optional<Users> optUser = usersRepository.findByToken(token);
        if(optUser.isEmpty()){
            res.put("success", false);
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

        res.put("success", true);
        res.put("groups", groups);
        return (LinkedHashMap) res;
    }

    @PostMapping
    public LinkedHashMap createOne(
            @RequestHeader("Authorization") String token,
            @RequestBody LinkedHashMap body
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = usersRepository.findByToken(token);
        if(optUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users user = optUser.get();

        String name = (String) body.get("name");
        Groups group = new Groups(name, user);

        String description = (String) body.getOrDefault("description", "");
        if(!description.isBlank()) group.setDescription(description);

        String photo = (String) body.getOrDefault("photo", "");
        if(!photo.isBlank()) group.setPhoto(photo);
        repository.save(group);

        User_Group membership = new User_Group(user, group, true);
        userGroupRepository.save(membership);

        res.put("success", true);
        res.put("group", group);
        return (LinkedHashMap) res;
    }

    @PostMapping(path = {"/{id}"})
    public LinkedHashMap edit(
            @RequestHeader("Authorization") String token,
            @RequestBody LinkedHashMap body,
            @PathVariable Long id
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = usersRepository.findByToken(token);
        if(optUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users user = optUser.get();

        Optional<User_Group> optMembership = userGroupRepository.findByUserIdAndGroupId(user.getId(), id);
        if(optMembership.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        User_Group membership = optMembership.get();
        if((membership.getExitedAt() != null) || (!membership.isAdmin())){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        Optional<Groups> optGroup = repository.findById(id);
        Groups group = optGroup.get();

        String description = (String) body.getOrDefault("description", "");
        if(!description.isBlank()) group.setDescription(description);

        String name = (String) body.getOrDefault("name", "");
        if(!name.isBlank()) group.setName(name);

        String photo = (String) body.getOrDefault("photo", "");
        if(!photo.isBlank()) group.setPhoto(photo);
        repository.save(group);

        res.put("success", true);
        res.put("group", group);
        return (LinkedHashMap) res;
    }

    List<Messages> findLastByGroup(Groups group){
        List<User_Group> userGroups = userGroupRepository.findByGroupId(group.getId());
        List<Messages> messagesList = messagesRepository.findAllByUserGroupInOrderByCreatedAt(userGroups);
        try {
            return List.of(messagesList.get(0));
        }
        catch (Exception e){
            return Collections.emptyList();
        }
    }
}
