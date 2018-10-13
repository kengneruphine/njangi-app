package com.example.njangiapp.model;

import java.util.ArrayList;

public class RoleDTO {
    private int id;
    private String name;
    private String description;
    private ArrayList<Integer> permissionIds;

    public RoleDTO() {
        super();
    }

    public RoleDTO(String name, String description, ArrayList<Integer> permissionIds) {
        super();
        this.name = name;
        this.description = description;
        this.permissionIds = permissionIds;
    }

    public RoleDTO(int id, String name, String description, ArrayList<Integer> permissionIds) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissionIds = permissionIds;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getPermissionIds() {
        return this.permissionIds;
    }

    public void setPermissionIds(ArrayList<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

}
