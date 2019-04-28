package com.springboot.model;


import com.springboot.configuration.FieldMatch;
import com.springboot.configuration.ValidPassword;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
@FieldMatch(field = "password", fieldMatch = "confirmPassword", message = "The password fields must match")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    @ValidPassword
    private String password;

    private String confirmPassword;

    @Column(name = "active")
    private int active;

    @Column(name = "role")
    @NotEmpty(message = "Please select a role!")
    private String role;

    @Column(name = "role_id")
    private int roleID;

    @Column(name = "routes")
    private String routes;

    @Column (name = "history")
    private String history;

    @Column(name = "Question_1")
    private String questionOne;

    @Column(name = "Question_2")
    private String questionTwo;

    @Column(name = "Question_3")
    private String questionThree;

    @Column(name = "Answer_1")
    private String answerOne;

    @Column(name = "Answer_2")
    private String answerTwo;

    @Column(name = "Answer_3")
    private String answerThree;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConfirmPassword() { return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public int getRoleID() { return roleID; }

    public void setRoleID(int roleID) { this.roleID = roleID; }

    public String getQuestionOne() { return questionOne; }

    public void setQuestionOne(String questionOne) { this.questionOne = questionOne; }

    public String getQuestionTwo() { return questionTwo; }

    public void setQuestionTwo(String questionTwo) { this.questionTwo = questionTwo; }

    public String getQuestionThree() { return questionThree; }

    public void setQuestionThree(String questionThree) { this.questionThree = questionThree; }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}