package com.example.uolcoursereview.dto;

import jakarta.validation.constraints.NotNull;

public class StudentRequest {

    @NotNull
    String name;
    @NotNull
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
