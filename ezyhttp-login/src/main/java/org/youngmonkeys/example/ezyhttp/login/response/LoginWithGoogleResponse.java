package org.youngmonkeys.example.ezyhttp.login.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LoginWithGoogleResponse extends BaseResponse {

    // Constructor with all args
    public LoginWithGoogleResponse(String accessToken, int status, int errorCode, String errorReason, LocalDateTime responseTime) {
        super(status, errorCode, errorReason, responseTime);
        AccessToken = accessToken;
    }

    // Constructor with no args
    public LoginWithGoogleResponse() {
        super();
    }

    // Access token
    private String AccessToken;
}
