package com.project.casualstore.model.entity;

import com.project.casualstore.model.entity.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class UserRoleEntity extends BaseEntity {
    private UserRoleEnum userRole;

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}