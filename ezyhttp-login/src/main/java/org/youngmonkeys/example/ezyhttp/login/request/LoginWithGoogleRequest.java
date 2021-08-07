package org.youngmonkeys.example.ezyhttp.login.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
// Google access token request
public class LoginWithGoogleRequest {
    // Access token
    private String AccessToken;

    // Expires At
    private LocalDateTime ExpiresAt;

    // Expires In
    private LocalDateTime ExpiresIn;

    // Time of first times Login
    private LocalDateTime FirstIssuedAt;

    // Id of token
    private String IdToken;

    // Email address
    private String EmailAddress;

    // Url of image profile
    private String ImageProfileUrl;

    // First name
    private String FirstName;

    // Last name
    private String LastName;

    // User name
    private String UserName;
}
