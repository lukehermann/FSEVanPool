package com.springboot.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PasswordForgotDto {

    @Email
    @NotEmpty
    private String email;

    private String questionOneAnswer;

    private String questionTwoAnswer;

    private String questionThreeAnswer;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestionOneAnswer() {
        return questionOneAnswer;
    }

    public void setQuestionOneAnswer(String questionOneAnswer) {
        this.questionOneAnswer = questionOneAnswer;
    }

    public String getQuestionTwoAnswer() {
        return questionTwoAnswer;
    }

    public void setQuestionTwoAnswer(String questionTwoAnswer) {
        this.questionTwoAnswer = questionTwoAnswer;
    }

    public String getQuestionThreeAnswer() {
        return questionThreeAnswer;
    }

    public void setQuestionThreeAnswer(String questionThreeAnswer) {
        this.questionThreeAnswer = questionThreeAnswer;
    }
}