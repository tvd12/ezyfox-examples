package org.youngmonkeys.example.ezyhttp.login.service;

import com.google.api.services.oauth2.model.Userinfo;
import org.youngmonkeys.example.ezyhttp.login.entity.User;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;

public interface IUserService {

    User getUserInfoByEmail(String email);

    User getUserById(long userId);

    User getUserByThirdPartyId(String thirdPartyId);

    User saveGoogleUserInfo(Userinfo googleUserInfo);

    User saveFacebookUserInfo(com.restfb.types.User user);

    void saveUser(User user);
}
