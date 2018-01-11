package com.maslen.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "UserActivity")
public class UserActivity {

    @Id
    @Column(name = "UserActivityID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userActivityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "Action", length = 50)
    @NotNull
    private String action;

    @Column(name = "Session", length = 50)
    @NotNull
    private String session;

    @Column(name = "Status", nullable = false)
    private char status;


}
