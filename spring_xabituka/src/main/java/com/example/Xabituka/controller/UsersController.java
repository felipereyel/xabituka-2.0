package com.example.Xabituka.controller;

import com.example.Xabituka.model.Users;
import com.example.Xabituka.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;

@RestController
@RequestMapping({"/users"})
public class UsersController {

    private UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping({"/sign-up"})
    public LinkedHashMap signup(@RequestBody LinkedHashMap body) {
        Map res = new LinkedHashMap();
        try {
            String nickname = (String) body.get("nickname");
            String full_name = (String) body.get("full_name");
            String psswd = (String) body.get("psswd");

            Optional<Users> existingUser = Optional.ofNullable(repository.findByNickname(nickname));

            if (existingUser.isPresent()) {
                res.put("created", false);
                res.put("reason", "Already user with nickname");
                res.put("user", body);
            }
            else {
                String token = RandomStringUtils.randomAlphanumeric(10);
                Users user = new Users();

                user.setNickname(nickname);
                user.setFullName(full_name);
                user.setPw(psswd);
                user.setToken(token);
                user.setCreatedAtNow();

                repository.save(user);

                res.put("created", true);
                res.put("user", user);
            }
        }
        catch(Exception e) {
            res.put("created", false);
            res.put("reason", "Unexpected error");
            res.put("user", body);
        }

        return (LinkedHashMap) res;
    }

    // Show de bola
    @GetMapping({"/login"})
    public LinkedHashMap login(@RequestParam String nickname, @RequestParam String psswd) {
        Map res = new LinkedHashMap();
        try {
            Users user = repository.findByNickname(nickname);

            if (user.getPw().equals(psswd)) {
                Map userHash = new LinkedHashMap();
                userHash.put("id", user.getId());
                userHash.put("fullName", user.getFullName());
                userHash.put("photo", user.getPhoto());
                userHash.put("createdAt", user.getCreatedAt());
                res.put("authorization", true);
                res.put("token", user.getToken());
                res.put("user", userHash);
            }
            else {
                res.put("authorization", false);
            }
        }
        catch(Exception e) {
            res.put("authorization", false);
        }

        return (LinkedHashMap) res;
    }
}
