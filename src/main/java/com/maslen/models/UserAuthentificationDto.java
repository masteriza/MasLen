//package com.maslen.models;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "users")
//public class UserAuthentificationDto {
//
//    private String username;
//    private String password;
//
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "phone_id", foreignKey = @ForeignKey(name = "FK_users_phone_id"))
//    private Phone phone;
//
//    @Column(name = "status", nullable = false, columnDefinition = "CHAR(1) default 'I'")
//    private char status;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_users_role_id"))
//    private Role role;
//
//    public UserAuthentificationDto(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//}
