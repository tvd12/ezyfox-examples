package org.youngmonkeys.example.ezyhttp.login.service;

public interface IAuthenticationService {

    long verifyAccessToken(String accessToken);

    String generateAccessToken(long userId);

    void removeAccessToken(String accessToken);
}
