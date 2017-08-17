package com.maslen.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    //@OneToMany(mappedBy = "role")
    private int roleId;
    @Column(name = "role_name")
    private String roleName;
}
