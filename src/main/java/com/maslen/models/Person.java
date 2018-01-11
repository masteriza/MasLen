package com.maslen.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "authorities")
@Entity
@Table(name = "Persons")
public class Person {

    @Id
    @Column(name = "PersonID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personID;

    @Column(name = "FirstName", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "LastName", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name = "MiddleName", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String middlename;

    @Column(name = "Sex")
    @NotNull
    private String sex;

    @Column(name = "Birthday")
    @NotNull
    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.ALL)
    //todo: сделать норм именование ключей
    @JoinColumn(name = "PhoneID", foreignKey = @ForeignKey(name = "FK_person_phone_id"))
    private Phone phone;

    @Column(name = "Status", nullable = false, columnDefinition = "CHAR(1) default 'I'")
    private char status;

}
