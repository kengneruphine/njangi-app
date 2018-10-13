package com.example.njangiapp.model;

import java.util.ArrayList;

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isActive;
    private ArrayList<Integer> roleIds;
    private String phoneNumber;

    public UserDTO() {
        super();
    }

    public UserDTO(int id, String firstName, String lastName, String username,String email, boolean isActive, int roleId, String phoneNumber) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.roleIds.add(roleId);
        this.phoneNumber = phoneNumber;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ArrayList<Integer> getRoleIds() {
        return this.roleIds;
    }

    public void setRoleIds(ArrayList<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
