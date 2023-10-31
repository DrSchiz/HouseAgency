package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String feedbackText;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private modelUser user;

    public Feedback() {}
    public Feedback(int id, String feedbackText, modelUser user) {
        this.id = id;
        this.feedbackText = feedbackText;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public modelUser getUser() {
        return user;
    }

    public void setUser(modelUser user) {
        this.user = user;
    }
}
