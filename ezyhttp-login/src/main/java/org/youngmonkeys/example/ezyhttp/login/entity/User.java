package org.youngmonkeys.example.ezyhttp.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@EqualsAndHashCode(of = "id", callSuper = false)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String fullName;
    private String firstName;
    private String lastName;
    private String avatarURL;
    private Date birthOfDate;
    private AccountType accountType;
    private int gender;
    private String password;
    private UserStatus status;
    private String thirdPartyId;
}
