package org.youngmonkeys.example.ezyhttp.login.service;

import com.google.api.services.oauth2.model.Userinfo;

/**
 * Interface of google login service
 */
public interface IGoogleService {

    Userinfo getUserInfoByAccessToken(String accessToken);

    String getAccessToken(String code);
}
