package com.parkingPg.parkingPg.repository;

import com.parkingPg.parkingPg.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepo extends JpaRepository<EmailVerification,Integer> {
    EmailVerification findByEmail(String email);
}
