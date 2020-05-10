package com.example.Xabituka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(name = "full_name")
    private String fullName;

    private String photo;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "psswd")
    private String pw;

    private String token;

    public Users(String nickname, String full_name, String psswd) {
        this.nickname = nickname;
        this.fullName = full_name;
        this.pw = psswd;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.token = RandomStringUtils.randomAlphanumeric(10);
    }

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
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
