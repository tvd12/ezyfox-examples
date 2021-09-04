package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.restfb.Version;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.client.HttpClientProxy;
import com.tvd12.ezyhttp.client.request.PostRequest;
import com.tvd12.ezyhttp.client.request.RequestEntity;
import com.tvd12.ezyhttp.core.constant.ContentTypes;
import org.youngmonkeys.example.ezyhttp.login.service.IFacebookService;

import java.util.Map;

/**
 * Implement class of interface facebook login service
 */
@EzySingleton
public class FacebookService extends EzyLoggable implements IFacebookService {

    @EzyProperty("facebook.client_id")
    private String clientId;

    @EzyProperty("facebook.client_secret")
    private String clientSecret;

    @EzyProperty("facebook.redirect_uri")
    private String redirectUri;

    @EzyProperty("facebook.get_token_url")
    private String getTokenUri;

    @EzyProperty("facebook.get_user_info_uri")
    private String getUserInfoUri;

    @EzyAutoBind
    private HttpClientProxy httpClientProxy;

    private static final int DEFAULT_GOOGLE_AUTH_TIMEOUT = 15 * 1000;

    @Override
    public User getUserInfoByAccessToken(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, clientSecret, Version.LATEST);
        return facebookClient.fetchObject("me", User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getAccessToken(String code) {
        Map<String, Object> requestBody = EzyMapBuilder.mapBuilder()
                .put("client_id", clientId)
                .put("client_secret", clientSecret)
                .put("redirect_uri", redirectUri)
                .put("code", code)
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
        }
        catch (Exception e) {
            logger.info("get facebook access token error", e);
            return null;
        }
    }
}
