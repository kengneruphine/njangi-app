package com.example.njangiapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usersys")
public class UserSys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="password" ,nullable = false)
    private String password;
    @Column(name="phone_number",nullable = false)
    private String phoneNumber;
    @Column(nullable=false)
    private boolean isActive;

    @Column(nullable=false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="usersys_role",
            joinColumns = @JoinColumn(name = "usersys_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public UserSys(){super();}

    public UserSys(String firstName, String lastName, String username, String email, String password, String phoneNumber, boolean isActive, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.roles = roles;
    }

    public UserSys(int id,String firstName, String lastName, String username, String email, String password, String phoneNumber, boolean isActive, List<Role> roles){
        this(firstName,lastName,username,email,password,phoneNumber,isActive,roles);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
