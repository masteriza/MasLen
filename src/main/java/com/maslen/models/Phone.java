package com.maslen.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhoneID", unique = true)
    private int phoneId;

    @Column(name = "Number", unique = true)
    private String number;

    @Column(name = "Status")
    private String status;
}
