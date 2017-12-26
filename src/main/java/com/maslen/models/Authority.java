package com.maslen.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "Authority")
public class Authority {

    @Id
    @Column(name = "AuthorityID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    @Column(name = "Name", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;
}