package org.youngmonkeys.example.ezyhttp.login.service;

import org.youngmonkeys.example.ezyhttp.login.entity.UserData;

public interface IUserDataService {
    void saveGoogleToken(long userId, String googleToken);
    void saveFacebookToken(long userId, String facebookToken, String facebookId);
}
