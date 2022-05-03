package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.client.HttpClientProxy;
import com.tvd12.ezyhttp.client.request.PostRequest;
import com.tvd12.ezyhttp.client.request.RequestEntity;
import com.tvd12.ezyhttp.core.constant.ContentTypes;
import lombok.Setter;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleService;

import java.util.Map;

/**
 * Implement class of interface google login service
 */
@Setter
@EzySingleton
public class GoogleService extends EzyLoggable implements IGoogleService {

    private static final int DEFAULT_GOOGLE_AUTH_TIMEOUT = 15 * 1000;
    @EzyProperty("google.client_id")
    private String clientId;
    @EzyProperty("google.client_secret")
    private String clientSecret;
    @EzyProperty("google.redirect_uri")
    private String redirectUri;
    @EzyProperty("google.get_token_uri")
    private String getTokenUri;
    @EzyAutoBind
    private HttpClientProxy httpClientProxy;

    @Override
    public Userinfo getUserInfoByAccessToken(String accessTokenStr) {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
            .setAccessToken(accessTokenStr);
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new GsonFactory(), credential)
            .setApplicationName("Oauth2")
            .build();
        try {
            return oauth2.userinfo().get().execute();
        } catch (Exception e) {
            logger.info("get google user information by access token error", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getAccessToken(String code) {
        Map<String, Object> requestBody = EzyMapBuilder.mapBuilder()
            .put("client_id", clientId)
            .put("client_secret", clientSecret)
            .put("redirect_uri", redirectUri)
            .put("code", code)
            .put("grant_type", "authorization_code")
            .build();
        PostRequest request = new PostRequest()
            .setURL(getTokenUri)
            .setEntity(
                RequestEntity.builder()
                    .contentType(ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED)
                    .body(requestBody)
                    .build()
            )
            .setResponseType(Map.class);
        try {
            Map<String, Object> response = httpClientProxy.call(request, DEFAULT_GOOGLE_AUTH_TIMEOUT);
            return (String) response.get("access_token");
        } catch (Exception e) {
            logger.info("get google access token error", e);
            return null;
        }
    }

}
