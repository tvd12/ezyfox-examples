package org.youngmonkeys.example.ezyhttp.thymeleaf.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private LocalDate birthOfDate;
}
