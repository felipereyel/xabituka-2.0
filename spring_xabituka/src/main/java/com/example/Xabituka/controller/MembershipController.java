package com.example.Xabituka.controller;

import com.example.Xabituka.model.Groups;
import com.example.Xabituka.model.User_Group;
import com.example.Xabituka.model.Users;
import com.example.Xabituka.repository.GroupsRepository;
import com.example.Xabituka.repository.MessagesRepository;
import com.example.Xabituka.repository.UserGroupRepository;
import com.example.Xabituka.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping({"/membership"})
public class MembershipController {

    private final UserGroupRepository repository;
    private final GroupsRepository groupsRepository;
    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;

    public MembershipController(
            UserGroupRepository repository,
            GroupsRepository groupsRepository,
            MessagesRepository messagesRepository,
            UsersRepository usersRepository
    ) {
        this.repository = repository;
        this.groupsRepository = groupsRepository;
        this.messagesRepository = messagesRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping(path = {"/{groupId}/add/{userId}"})
    public LinkedHashMap add(
            @RequestHeader("Authorization") String token,
            @PathVariable Long groupId,
            @PathVariable Long userId,
            @RequestParam(defaultValue = "false") boolean admin
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optSelfUser = usersRepository.findByToken(token);
        if(optSelfUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users selfUser = optSelfUser.get();

        Optional<User_Group> optMembership = repository.findFirstByUserIdAndGroupIdOrderByJoinedAt(selfUser.getId(), groupId);
        if(optMembership.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }
        User_Group selfMembership = optMembership.get();

        if((selfMembership.getExitedAt() != null) || (!selfMembership.isAdmin())){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        Optional<Groups> optGroup = groupsRepository.findById(groupId);
        Groups group = optGroup.get();

        Optional<Users> optUser = usersRepository.findById(userId);
        if(!optUser.isPresent()){
            res.put("success", false);
            res.put("reason", "Invalid user");
            return (LinkedHashMap) res;
        }
        Users user = optUser.get();


        User_Group membership = new User_Group(user, group, admin);
        repository.save(membership);

        res.put("success", true);
        res.put("membership", membership);
        return (LinkedHashMap) res;
    }

    @GetMapping(path = {"/{groupId}/remove/{userId}"})
    public LinkedHashMap remove(
            @RequestHeader("Authorization") String token,
            @PathVariable Long groupId,
            @PathVariable Long userId
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optSelfUser = usersRepository.findByToken(token);
        if(optSelfUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users selfUser = optSelfUser.get();

        Optional<User_Group> optSelfMembership = repository.findFirstByUserIdAndGroupIdOrderByJoinedAt(selfUser.getId(), groupId);
        if(optSelfMembership.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }
        User_Group selfMembership = optSelfMembership.get();

        if(selfMembership.getExitedAt() != null){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        if(selfUser.getId().equals(userId)){
            selfMembership.setExitedAt();
            repository.save(selfMembership);
            res.put("success", true);
            res.put("membership", selfMembership);
            return (LinkedHashMap) res;
        }

        if(!selfMembership.isAdmin()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }

        Optional<User_Group> optMembership = repository.findFirstByUserIdAndGroupIdOrderByJoinedAt(userId, groupId);
        if(!optMembership.isPresent()){
            res.put("success", false);
            res.put("reason", "Invalid membership");
            return (LinkedHashMap) res;
        }
        User_Group membership = optMembership.get();
        membership.setExitedAt();
        repository.save(membership);
        res.put("success", true);
        res.put("membership", membership);
        return (LinkedHashMap) res;
    }
}
