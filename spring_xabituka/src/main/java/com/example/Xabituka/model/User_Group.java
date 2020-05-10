package com.example.Xabituka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@Entity
@Data
public class User_Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;

    @Column(name = "joined_at")
    private Time joinedAt;

    @Column(name = "exited_at")
    private Time exitedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Time joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Time getExitedAt() {
        return exitedAt;
    }

    public void setExitedAt(Time exitedAt) {
        this.exitedAt = exitedAt;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }
}
