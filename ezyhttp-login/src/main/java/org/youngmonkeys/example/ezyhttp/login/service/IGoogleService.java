package org.youngmonkeys.example.ezyhttp.login.service;

import com.google.api.services.oauth2.model.Userinfo;
import org.youngmonkeys.example.ezyhttp.login.request.LoginWithGoogleRequest;
import org.youngmonkeys.example.ezyhttp.login.response.LoginWithGoogleResponse;

import java.io.IOException;

/**
 * Interface of google login service
 */
public interface IGoogleService {

    Userinfo getUserInfoByAccessToken(String accessToken) throws Exception;

    String getAccessToken(String code) throws IOException;

}
