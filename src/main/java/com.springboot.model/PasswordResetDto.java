package com.springboot.model;

import com.springboot.configuration.FieldMatch;
import com.springboot.configuration.ValidPassword;
import org.hibernate.validator.constraints.NotEmpty;

@FieldMatch(field = "password", fieldMatch = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}