package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String adress;
    private boolean sells;
    @NonNull
    @Min(10000)
    private int price;
    @NotBlank
    private String description;
    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name="user_id")
    private modelUser user;
    @ManyToOne
    @JoinColumn(name="insuranceAgency_id")
    private InsuranceAgency insuranceAgency;
    @ManyToOne
    @JoinColumn(name="statusHouse_id")
    private StatusHouse statusHouse;

    public House() {}

    public House(int id, String adress, boolean sells, int price, String description, City city, modelUser user, InsuranceAgency insuranceAgency, StatusHouse statusHouse) {
        this.id = id;
        this.adress = adress;
        this.sells = sells;
        this.price = price;
        this.description = description;
        this.city = city;
        this.user = user;
        this.insuranceAgency = insuranceAgency;
        this.statusHouse = statusHouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isSells() {
        return sells;
    }

    public void setSells(boolean sells) {
        this.sells = sells;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public modelUser getUser() {
        return user;
    }

    public void setUser(modelUser user) {
        this.user = user;
    }

    public InsuranceAgency getInsuranceAgency() {
        return insuranceAgency;
    }

    public void setInsuranceAgency(InsuranceAgency insuranceAgency) {
        this.insuranceAgency = insuranceAgency;
    }

    public StatusHouse getStatusHouse() {
        return statusHouse;
    }

    public void setStatusHouse(StatusHouse statusHouse) {
        this.statusHouse = statusHouse;
    }
}
