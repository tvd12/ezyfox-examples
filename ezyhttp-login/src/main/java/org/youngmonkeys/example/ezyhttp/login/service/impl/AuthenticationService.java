package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.security.EzySHA256;
import org.youngmonkeys.example.ezyhttp.login.entity.AccessToken;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenExpiredException;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenNotFoundException;
import org.youngmonkeys.example.ezyhttp.login.repository.AccessTokenRepository;
import org.youngmonkeys.example.ezyhttp.login.service.IAuthenticationService;

import java.time.LocalDateTime;
import java.util.UUID;

@EzySingleton
public class AuthenticationService implements IAuthenticationService {

    @EzyProperty("access_token.expires_in")
    private int expireIn;

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    public long verifyAccessToken(String accessToken) {
        LocalDateTime now = LocalDateTime.now();
        AccessToken accessTokenObj = accessTokenRepository.findById(accessToken);
        if (accessTokenObj == null) {
            throw new TokenNotFoundException("token: " + accessToken + " not found");
        } else if (accessTokenObj.getExpireIn().isBefore(now)) {
            throw new TokenExpiredException("token: " + accessToken + " expired");
        }
        return accessTokenObj.getUserId();
    }

    @Override
    public String generateAccessToken(long userId) {
        String tokenString = EzySHA256.cryptUtfToLowercase(
            userId + UUID.randomUUID().toString() + System.currentTimeMillis()
        );
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(tokenString);
        accessToken.setExpireAt(LocalDateTime.now());
        accessToken.setExpireIn(LocalDateTime.now().plusSeconds(expireIn));
        accessToken.setFirstIssueAt(LocalDateTime.now());
        accessToken.setUserId(userId);
        accessTokenRepository.save(accessToken);
        return tokenString;
    }

    @Override
    public void removeAccessToken(String accessToken) {
        accessTokenRepository.delete(accessToken);
    }
}
