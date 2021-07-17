package org.youngmonkeys.example.ezyhttp.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_information")
@EqualsAndHashCode(of = "id", callSuper = false)
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String fullName;
    private String thirdPartyId;
    private String avatarURL;
    private LocalDate birthOfDate;
    private AccountType accountType;
    private LocalDateTime registerTime;
    private LocalDateTime lastUpdatedTime;
}