package com.example.njangiapp.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column ( name ="name", nullable = false)
    private String name;
    @Column(name="description", nullable = false)
    private String description;

    @Column(nullable=false)
    @ManyToMany(mappedBy="roles")
    private List<UserSys> userSys;


    @Column(nullable=false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="role_permission",
            joinColumns=@JoinColumn(name="role_id", nullable=false),
            inverseJoinColumns=@JoinColumn(name="permission_id", nullable=false))
    private List<Permission> permissions;

    public Role() {
        super();
    }

    public Role(String name, String description, List<Permission> permissions) {
        super();
        this.name = name;
        this.description = description;
        this.permissions = permissions;
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

    public List<Permission> getPermission() {
        return this.permissions;
    }

    public void setPermission(List<Permission> permission) {
        this.permissions = permission;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }



}
