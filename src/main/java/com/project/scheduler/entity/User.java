package com.project.scheduler.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "occupant")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @NotEmpty
    @Column
    private String username;
    @NotEmpty
    @Column
    private String password;
    @NotEmpty
    @Column
    private String email;
    @Column
    private String phone;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> authorEvents;
    @ManyToMany
    @JoinTable(
            name = "events_occupants",
            joinColumns = @JoinColumn(name = "occupant_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;
    @ManyToMany
    @JoinTable(
            name = "occupants_terms",
            joinColumns = @JoinColumn(name = "occupant_id"),
            inverseJoinColumns = @JoinColumn(name = "term_id")
    )
    private Set<Date> dates;
    @ManyToMany
    @JoinTable(
            name = "occupants_places",
            joinColumns = @JoinColumn(name = "occupant_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private Set<Place> places;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Event> getAuthorEvents() {
        return authorEvents;
    }

    public void setAuthorEvents(Set<Event> authorEvents) {
        this.authorEvents = authorEvents;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
