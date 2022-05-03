package com.project.casualstore.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    @NotBlank
    @Size(min=4, max=20)
    private String firstName;
    @NotBlank
    @Size(min=4, max=20)
    private String lastName;
    @NotBlank
    @Size(min=4, max=20)
    private String password;
    @NotBlank
    @Size(min=4, max=20)
    private String confirmPassword;
    @NotBlank
    @Size(min=4, max=20)
    private String username;
    @NotBlank
    @Email
    @Size(min=4, max=20)
    private String email;

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}