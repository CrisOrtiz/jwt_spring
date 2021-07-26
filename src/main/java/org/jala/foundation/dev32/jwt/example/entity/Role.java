package org.jala.foundation.dev32.jwt.example.entity;

import org.jala.foundation.dev32.jwt.example.enums.RoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role() {
    }

    public Role(@NotNull RoleName roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRolName() {
        return roleName;
    }

    public void setRolName(RoleName roleName) {
        this.roleName = roleName;
    }
}
