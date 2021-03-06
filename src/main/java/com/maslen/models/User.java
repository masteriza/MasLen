package com.maslen.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;
    private String email;
    private String username;
    private String password;
    private char sex;
    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phoneId", foreignKey = @ForeignKey(name = "FK_users_phone_id"))
    private Phone phone;

    @Column(name = "status", nullable = false, columnDefinition = "CHAR(1) default 'I'")
    private char status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "FK_users_role_id"))
    private Role role;

    private boolean isActivated;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
