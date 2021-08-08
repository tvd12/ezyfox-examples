package org.youngmonkeys.example.ezyhttp.thymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private LocalDate birthOfDate;
    private LocalDate lastLoginDate;
    private LocalDateTime registerTime;
}
