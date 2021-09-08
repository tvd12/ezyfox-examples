package org.youngmonkeys.example.ezyhttp.login.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String accessToken;
}
