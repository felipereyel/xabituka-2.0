package com.example.Xabituka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "joined_at")
    private Timestamp joinedAt;

    @Column(name = "exited_at")
    private Timestamp exitedAt;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Timestamp getJoinedAt() { return joinedAt; }

    public void setJoinedAt(Timestamp joinedAt) { this.joinedAt = joinedAt; }

    public Timestamp getExitedAt() { return exitedAt; }

    public void setExitedAt(Timestamp exitedAt) { this.exitedAt = exitedAt; }

    public Users getUser() { return user; }

    public void setUser(Users user) { this.user = user; }

    public Groups getGroup() { return group; }

    public void setGroup(Groups group) { this.group = group; }
}
