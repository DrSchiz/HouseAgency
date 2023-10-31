package com.example.springmodels.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


public class modelUser {
    private int id;
    @NotBlank(message = "Поле логин не должно быть пустым!")
    @Size(min = 3, max = 20, message = "Длина логина должна быть в пределах 3 и 20 символов!")
    private String username;
    @NotBlank(message = "Поле пароль не должно быть пустым!")
    private String password;
    private boolean active;
    private Passport passport;
    private List<Feedback> feedbacks;

    private List<House> houses;
//    @ElementCollection(targetClass = roleEnum.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
    private Set<roleEnum> roles;

    public modelUser() {}
    public modelUser(int id, String username, String password, boolean active, Passport passport, List<Feedback> feedbacks, List<House> houses, Set<roleEnum> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.passport = passport;
        this.feedbacks = feedbacks;
        this.houses = houses;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public Set<roleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<roleEnum> roles) {
        this.roles = roles;
    }
}
