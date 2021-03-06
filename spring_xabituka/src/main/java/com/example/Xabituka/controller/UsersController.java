package com.example.Xabituka.controller;

import com.example.Xabituka.model.Users;
import com.example.Xabituka.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping({"/users"})
public class UsersController {

    private final UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public LinkedHashMap findAll() {
        Map res = new LinkedHashMap();
        res.put("success", true);
        res.put("users", repository.findAll());
        return (LinkedHashMap) res;
    }

    @PostMapping
    public LinkedHashMap editSelf(
        @RequestHeader("Authorization") String token,
        @RequestBody LinkedHashMap body
    ) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = repository.findByToken(token);
        if(optUser.isEmpty()){
            res.put("success", false);
            res.put("reason", "Invalid token");
            return (LinkedHashMap) res;
        }
        Users user = optUser.get();

        String fullName = (String) body.getOrDefault("fullName", "");
        if(!fullName.isBlank()) user.setFullName(fullName);

        String photo = (String) body.getOrDefault("photo", "");
        if(!photo.isBlank()) user.setPhoto(photo);

        String psswd = (String) body.getOrDefault("psswd", "");
        if(!psswd.isBlank()) user.setPw(psswd);

        repository.save(user);

        res.put("success", true);
        res.put("user", user);
        return (LinkedHashMap) res;
    }

    @PostMapping({"/sign-up"})
    public LinkedHashMap signup(@RequestBody LinkedHashMap body) {
        Map res = new LinkedHashMap();

        String nickname = (String) body.get("nickname");
        String full_name = (String) body.get("full_name");
        String psswd = (String) body.get("psswd");

        Optional<Users> existingUser = repository.findByNickname(nickname);
        if (existingUser.isPresent()) {
            res.put("success", false);
            res.put("reason", "Already user with nickname");
            return (LinkedHashMap) res;
        }

        Users user = new Users(
            nickname,
            full_name,
            psswd
        );

        repository.save(user);
        res.put("success", true);
        res.put("user", user);
        res.put("token", user.getToken());
        return (LinkedHashMap) res;
    }

    @GetMapping({"/login"})
    public LinkedHashMap login(@RequestParam String nickname, @RequestParam String psswd) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = repository.findByNickname(nickname);
        if (optUser.isEmpty()) {
            res.put("success", false);
            res.put("reason", "Wrong data");
            return (LinkedHashMap) res;
        }

        Users user = optUser.get();
        if (!user.getPw().equals(psswd)) {
            res.put("success", false);
            res.put("reason", "Wrong data");
            return (LinkedHashMap) res;
        }

        res.put("success", true);
        res.put("user", user);
        res.put("token", user.getToken());
        return (LinkedHashMap) res;
    }

//    return repository.findById(id)
//            .map(record -> ResponseEntity.ok().body(record))
//            .orElse(ResponseEntity.notFound().build());
}
