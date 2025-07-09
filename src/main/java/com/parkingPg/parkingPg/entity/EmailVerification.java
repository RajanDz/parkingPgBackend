package com.parkingPg.parkingPg.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification", indexes = {
        @Index(name = "idx_email", columnList = "email")
})
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "code")
    private Long code;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @NonNull
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @NonNull
    @Column(name = "verified")
    private Boolean verified;
}