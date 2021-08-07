package org.youngmonkeys.example.ezyhttp.login.service;

import org.youngmonkeys.example.ezyhttp.login.request.LoginWithGoogleRequest;
import org.youngmonkeys.example.ezyhttp.login.response.LoginWithGoogleResponse;

/**
 * Interface of google login service
 */
public interface IGoogleLoginService {
    /**
     * verify id token
     * @param idToken
     * @return true: success, false: failure
     * @throws Exception
     */
    boolean verifyGoogleIdToken(String idToken) throws Exception;

    /**
     * login with google logic
     * @param request login request
     * @return login response
     * @throws Exception
     */
    LoginWithGoogleResponse loginWithGoogle(LoginWithGoogleRequest request) throws Exception;

    /**
     * sign out user login with google
     * @param accessToken
     */
    void signOut(String accessToken);
}
