package com.maslen.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "authorities")
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "user_seq"*/)
    //@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long userId;

    @Column(name = "UserName", length = 50, unique = true)
//    @NotNull
//    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "Password", length = 100)
//    @NotNull
//    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "FirstName", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "LastName", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name = "Email", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "Enabled")
//    @NotNull
    private Boolean enabled;

    @Column(name = "LastPasswordResetDate")
//    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserAuthority",
            joinColumns = {@JoinColumn(name = "UserID"/*, referencedColumnName = "UserID"*/)},
            inverseJoinColumns = {@JoinColumn(name = "AuthorityID"/*, referencedColumnName = "AuthorityID"*/)})
    private List<Authority> authorities;

    public User(String email) {
        this.email = email;
    }

    public User(String email, List<Authority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public List<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<Authority> authorities) {
//        this.authorities = authorities;
//    }
//
//    public Date getLastPasswordResetDate() {
//        return lastPasswordResetDate;
//    }
//
//    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
//        this.lastPasswordResetDate = lastPasswordResetDate;
//    }
}