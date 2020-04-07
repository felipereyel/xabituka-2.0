package com.example.Xabituka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@Entity
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photo;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "created_at")
    private Time createdAt;
    private String description;
}
