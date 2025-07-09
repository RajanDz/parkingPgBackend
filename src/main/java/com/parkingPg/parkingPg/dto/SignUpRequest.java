package com.parkingPg.parkingPg.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
