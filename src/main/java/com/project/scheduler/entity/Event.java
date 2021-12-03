package com.project.scheduler.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @NotNull
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "events"
    )
    private Set<User> users;
    @ManyToMany
    private Set<Date> dates;
    @ManyToMany
    private Set<Place> places;
/*
    public Set<Event> findByUserId(long id) {
        Set<Event> events = (Set<Event>) new ArrayList<Event>();
        for () {

        }
        events.add()
        return events;
    }
*/
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

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<Date> dates) {
        this.dates = dates;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
