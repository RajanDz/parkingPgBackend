package com.parkingPg.parkingPg.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "user_option")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @Column(name = "email_verification")
    private Boolean emailVerification;
}
