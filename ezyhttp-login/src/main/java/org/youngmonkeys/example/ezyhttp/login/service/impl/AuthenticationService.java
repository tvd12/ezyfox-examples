package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.example.ezyhttp.login.entity.AccessToken;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenExpiredException;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenNotFoundException;
import org.youngmonkeys.example.ezyhttp.login.repository.AccessTokenRepository;
import org.youngmonkeys.example.ezyhttp.login.service.IAuthenticationService;

import java.time.LocalDateTime;

@EzySingleton
public class AuthenticationService implements IAuthenticationService {

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    public long verifyAccessToken(String accessToken) {
        LocalDateTime now = LocalDateTime.now();

        AccessToken accessTokenObj = accessTokenRepository.findById(accessToken);
        if (accessTokenObj == null ) {
            throw new TokenNotFoundException("token: " + accessToken + " not found");
        }
        else if (accessTokenObj.getExpireIn().isBefore(now)) {
            throw new TokenExpiredException("token: " + accessToken + " expired");
        }

        return accessTokenObj.getUserId();
    }
}
