package com.example.Xabituka.controller;

import com.example.Xabituka.model.User_Group;
import com.example.Xabituka.model.Users;
import com.example.Xabituka.model.Groups;
import com.example.Xabituka.repository.GroupsRepository;
import com.example.Xabituka.repository.UserGroupRepository;
import com.example.Xabituka.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/groups"})
public class GroupsController {

//    private GroupsRepository repository;
//    private UserGroupRepository userGroupRepository;
//    private UsersRepository usersRepository;
//
//    public GroupsController(GroupsRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping
//    public List findAll(@RequestParam String token) {
//        Long userId = usersRepository.findByToken(token).getId();
//
//        List<Long> allowedGroupIds = userGroupRepository
//                .findByUserId(userId)
//                .stream()
//                .filter(it -> it.getExitedAt().isNull())
//                .map(it -> it.getGroupId())
//                .collect(Collectors.toList());
//
//        return repository.findByIdIn(allowedGroupIds);
//    }
//
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
//
//    @PostMapping
//    public Users create(@RequestBody Users user) {
//        return repository.save(user);
//    }
//
//    @GetMapping({"/login"})
//    public LinkedHashMap teste(@RequestParam String nickname, @RequestParam String pw) {
//        Map res = new LinkedHashMap();
//        try {
//            Users user = repository.findByNickname(nickname);
//
//            if (user.getPw().equals(pw)) {
//                res.put("auth", true);
//                res.put("id", user.getId());
//                res.put("fullName", user.getFullName());
//                res.put("createdAt", user.getCreatedAt());
//                res.put("token", user.getToken());
//            }
//            else {
//                res.put("auth", false);
//            }
//        }
//        catch(Exception e) {
//            res.put("auth", false);
//        }
//
//        return (LinkedHashMap) res;
//    }
}
