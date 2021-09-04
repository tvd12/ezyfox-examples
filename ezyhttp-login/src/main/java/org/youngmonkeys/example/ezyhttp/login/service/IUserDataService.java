package org.youngmonkeys.example.ezyhttp.login.service;

public interface IUserDataService {
    void saveGoogleToken(long userId, String googleToken);
    void saveFacebookToken(long userId, String facebookToken);
}
