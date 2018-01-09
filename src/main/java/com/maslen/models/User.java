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
@Table(name = "User")
public class User {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "PersonID", foreignKey = @ForeignKey(name = "FK_users_persons_id"))
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "personID", cascade = CascadeType.ALL)
    private Long userId;

    @Column(name = "UserName", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "Password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

//    @Column(name = "FirstName", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
//    private String firstname;
//
//    @Column(name = "LastName", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
//    private String lastname;

    @Column(name = "Email", length = 50)
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

    //    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "UserAuthority",
//            joinColumns = {@JoinColumn(name = "UserID"/*, referencedColumnName = "UserID"*/)},
//            inverseJoinColumns = {@JoinColumn(name = "AuthorityID"/*, referencedColumnName = "AuthorityID"*/)})

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "FK_user_authority_id")/* , referencedColumnName = "AuthorityID"*/)
    private List<Authority> authorities;

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