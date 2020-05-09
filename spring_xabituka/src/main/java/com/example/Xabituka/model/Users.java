package com.example.Xabituka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "psswd")
    private String pw;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String token;

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getPw() {
        return pw;
    }

    public String getToken() {
        return token;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreatedAtNow() {
        this.createdAt = new Timestamp(System.currentTimeMillis());;
    }
}
