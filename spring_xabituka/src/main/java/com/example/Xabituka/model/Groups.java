package com.example.Xabituka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@Entity
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner;

    @Column(name = "created_at")
    private Time createdAt;

    private String description;

    public LinkedHashMap toMapWithContext(Messages lastMessage){
        Map repr = new LinkedHashMap();
        repr.put("id", this.getId());
        repr.put("name", this.getName());
        repr.put("photo", this.getPhoto());
        repr.put("owner", this.getOwner());
        repr.put("createdAt", this.getCreatedAt());
        repr.put("description", this.getDescription());
        repr.put("lastMessage", lastMessage);
        return (LinkedHashMap) repr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }
}
