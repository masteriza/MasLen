package com.maslen.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Authority")
public class Authority {

    @Id
    @Column(name = "AuthorityID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

//    @Column(name = "UserAuthorityID")
//    private Long userAuthorityId;

    @Column(name = "Name", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    //    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;
}