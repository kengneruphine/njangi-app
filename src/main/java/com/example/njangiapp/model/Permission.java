package com.example.njangiapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="name",nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy="permissions")
    private List<Role> roles;


    public Permission(){super();}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
