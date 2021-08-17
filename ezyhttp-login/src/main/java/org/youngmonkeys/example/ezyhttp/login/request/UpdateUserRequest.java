package org.youngmonkeys.example.ezyhttp.login.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateUserRequest {
    private long Id;
    private String FirstName;
    private String LastName;
    private String Password;
    private String AccessToken;
}
