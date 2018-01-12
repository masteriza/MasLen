package com.maslen.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "authorities")
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "UserID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "UserName", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "Password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "Email", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "Enabled")
    @NotNull
    private Boolean enabled;

    @Column(name = "isActivated")
    @NotNull
    private Boolean isActivated;

    @Column(name = "LastPasswordResetDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "FK_user_authority_id"))
    private List<Authority> authorities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "FK_user_userActivity_id"))
    private List<UserActivity> userActivity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PersonID")
    private Person person;

    public User(String email) {
        this.email = email;
    }

    public User(String email, List<Authority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }
}