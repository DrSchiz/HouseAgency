package com.example.springmodels.models;



import javax.validation.constraints.NotBlank;
import java.util.List;


public class City {
    private int id;
    @NotBlank
    private String name;
    private List<House> houses;

    public City() {}
    public City(int id, String name, List<House> houses) {
        this.id = id;
        this.name = name;
        this.houses = houses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
