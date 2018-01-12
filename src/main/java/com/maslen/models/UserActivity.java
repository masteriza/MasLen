package com.maslen.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @Column(name = "CreateDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;

    @Column(name = "EndDateTime")
    private LocalDateTime endDate;

    @Column(name = "Action", length = 50)
    @NotNull //R - restore password; E - entrance user; C - confirm email; S - registration user
    private char action;

    @Column(name = "Session", length = 50)
    @NotNull
    private String session;

    @Column(name = "Status", nullable = false)
    private char status;


}
