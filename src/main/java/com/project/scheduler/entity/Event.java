package com.project.scheduler.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToMany
    private Set<User> users;

    public User getAuthor() {
        return author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
