package org.youngmonkeys.example.ezyhttp.login.service;

import com.google.api.services.oauth2.model.Userinfo;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;

public interface ILoginService {
    UserInformation getUserInfoByEmail(String email);
    UserInformation getUserById(long userId);
    void saveUserInfoGoogle(Userinfo userinfo, String accessTokenGoogle);
    boolean saveUserInformation(UpdateUserRequest request);
    void saveAccessToken(long userId, String accessTokenGoogle);
    void logout(String accessToken);
}
