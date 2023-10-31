package com.example.springmodels.models;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Passport {

    private int id;
    @NotNull
    private int series;
    @NonNull
    private int number;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;

    private modelUser user;

    public Passport() {}
    public Passport(int id, int series, int number, String name, String lastName, modelUser user) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.name = name;
        this.lastName = lastName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public modelUser getUser() {
        return user;
    }

    public void setUser(modelUser user) {
        this.user = user;
    }
}
