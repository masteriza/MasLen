//package com.maslen.models;
//
//import com.maslen.security.model.Authority;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "userId")
//    private int userId;
//    private String email;
//    private String username;
//    private String password;
//    private char sex;
//    private LocalDate birthday;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "phoneId", foreignKey = @ForeignKey(name = "FK_users_phone_id"))
//    private Phone phone;
//
//    @Column(name = "status", nullable = false, columnDefinition = "CHAR(1) default 'I'")
//    private char status;
//
//    @Column(name = "lastPasswordResetDate")
//    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
//    private Date lastPasswordResetDate;
//
////    @OneToOne(cascade = CascadeType.ALL)
////    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "FK_users_role_id"))
////    private Role role;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "userAuthorities",
//            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
//            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
//    private List<Authority> userAuthorities;
//
//
//    private boolean isActivated;
//
//    public User(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//}
