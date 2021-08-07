package org.youngmonkeys.example.ezyhttp.login.service.iplm;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import org.youngmonkeys.example.ezyhttp.login.entity.AccessToken;
import org.youngmonkeys.example.ezyhttp.login.entity.AccountType;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.repository.AccessTokenRepository;
import org.youngmonkeys.example.ezyhttp.login.repository.UserInformationRepository;
import org.youngmonkeys.example.ezyhttp.login.request.LoginWithGoogleRequest;
import org.youngmonkeys.example.ezyhttp.login.response.LoginWithGoogleResponse;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleLoginService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@EzySingleton
/**
 * Implement class of interface google login service
 */
public class GoogleLoginService implements IGoogleLoginService {

    @EzyProperty("google.verify_url")
    private String VerifyUrl;

    @EzyAutoBind
    private UserInformationRepository userInformationRepository;

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    /**
     * verify id token
     * @param idToken
     * @return true: success, false: failure
     * @throws Exception
     */
    public boolean verifyGoogleIdToken(String idToken) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(VerifyUrl))
                .build();

        // (Receive idTokenString by HTTPS POST)
        GoogleIdToken googleIdToken = verifier.verify(idToken);

        // return true if result has value
        return googleIdToken != null;
    }

    @Override
    /**
     * login with google logic
     * @param request login request
     * @return login response
     * @throws Exception
     */
    public LoginWithGoogleResponse loginWithGoogle(LoginWithGoogleRequest request) throws Exception {
        LoginWithGoogleResponse response = new LoginWithGoogleResponse();

        // verify id token
        boolean idVerified = verifyGoogleIdToken(request.getIdToken());
        if (!idVerified) {
            response = new LoginWithGoogleResponse(null, 400, 400, "verify id token failure", LocalDateTime.now());
        } else {
            // create new access token string
            String accessTokenStr = EzySHA256.cryptUtfToLowercase(request.getAccessToken() + UUID.randomUUID());

            // check email exist in database
            UserInformation userInformation = userInformationRepository.findByField("email", request.getEmailAddress());

            // check user information,
            if (userInformation != null) {
                response = new LoginWithGoogleResponse(accessTokenStr, 200, 200, "user existed", LocalDateTime.now());
            } else {
                // create new user information
                userInformation = new UserInformation();
                userInformation.setEmail(request.getEmailAddress());
                userInformation.setFullName(request.getFirstName());
                userInformation.setLastName(request.getLastName());
                userInformation.setAvatarURL(request.getImageProfileUrl());
                userInformation.setAccountType(AccountType.GOOGLE);
                userInformation.setCreateTime(LocalDateTime.now());
                userInformation.setUpdateTime(LocalDateTime.now());
                userInformation.setVersion(1);
                userInformation.setDeleted(false);

                // save user information
                userInformationRepository.save(userInformation);
                response = new LoginWithGoogleResponse(accessTokenStr, 300, 300, "user not exist", LocalDateTime.now());
            }

            // create new access token
            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(accessTokenStr);
            accessToken.setIdToken(request.getIdToken());
            accessToken.setExpireAt(request.getExpiresAt());
            accessToken.setExpireIn(request.getExpiresIn());
            accessToken.setFirstIssueAt(request.getFirstIssuedAt());
            accessToken.setUpdateTime(LocalDateTime.now());
            accessToken.setUserId(userInformation.getId());
            accessToken.setCreateTime(LocalDateTime.now());
            accessToken.setVersion(1);
            // save access token
            accessTokenRepository.save(accessToken);
        }
        return response;
    }

    @Override
    public void signOut(String accessToken) {
        // get idToken by accessToken
        AccessToken accessTokenObj = accessTokenRepository.findById(accessToken);
    }
}
