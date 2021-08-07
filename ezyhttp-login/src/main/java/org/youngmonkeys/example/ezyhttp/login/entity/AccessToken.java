package org.youngmonkeys.example.ezyhttp.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "access_token")
@EqualsAndHashCode(of = "accessToken", callSuper = false)
public class AccessToken extends BaseEntity {
    @Id
    private String accessToken;
    private String idToken;
    private long userId;
    private LocalDateTime expireAt;
    private LocalDateTime expireIn;
    private LocalDateTime firstIssueAt;
}
