package org.youngmonkeys.example.ezyhttp.login.service;

public interface IAuthenticationService {
    long verifyAccessToken(String accessToken);
}
