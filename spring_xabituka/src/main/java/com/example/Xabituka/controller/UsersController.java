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
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Show demais
    @PostMapping({"/sign-up"})
    public LinkedHashMap signup(@RequestBody LinkedHashMap body) {
        Map res = new LinkedHashMap();

        String nickname = (String) body.get("nickname");
        String full_name = (String) body.get("full_name");
        String psswd = (String) body.get("psswd");

        Optional<Users> existingUser = Optional.ofNullable(repository.findByNickname(nickname));
        if (existingUser.isPresent()) {
            res.put("success", false);
            res.put("reason", "Already user with nickname");
            res.put("user", body);
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
        return (LinkedHashMap) res;
    }

    // Show de bola
    @GetMapping({"/login"})
    public LinkedHashMap login(@RequestParam String nickname, @RequestParam String psswd) {
        Map res = new LinkedHashMap();

        Optional<Users> optUser = Optional.ofNullable(repository.findByNickname(nickname));
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
        return (LinkedHashMap) res;
    }
}
