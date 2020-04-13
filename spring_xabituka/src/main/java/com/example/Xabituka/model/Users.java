package com.example.Xabituka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Users {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(name = "full_name")
    private String fullName;

    private String photo;

    @Column(name = "created_at")
    private Time createdAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "psswd")
    private String pw;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String token;
}
