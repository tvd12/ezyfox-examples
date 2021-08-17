package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.example.ezyhttp.login.repository.AccessTokenRepository;
import org.youngmonkeys.example.ezyhttp.login.repository.UserInformationRepository;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleService;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@EzySingleton
/**
 * Implement class of interface google login service
 */
public class GoogleService implements IGoogleService {

    @EzyProperty("google.verify_url")
    private String VerifyUrl;

    @EzyProperty("google.client_id")
    private String ClientId;

    @EzyProperty("google.secret_key")
    private String SecretKey;

    @EzyProperty("google.redirect_uri")
    private String RedirectUri;

    @EzyProperty("google.get_token_uri")
    private String GetTokenUri;

    @EzyProperty("google.get_user_info_uri")
    private String GetUserInfoUri;

    @EzyAutoBind
    private UserInformationRepository userInformationRepository;

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    public Userinfo getUserInfoByAccessToken(String accessTokenStr) throws Exception {
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessTokenStr);
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new GsonFactory(), credential).setApplicationName(
                "Oauth2").build();
        Userinfo userinfo = oauth2.userinfo().get().execute();
        return userinfo;
    }

    @Override
    public String getAccessToken(String code) {
        try {
            String link = GetTokenUri;
            String response = Request.Post(link)
                    .bodyForm(Form.form().add("client_id", ClientId)
                            .add("client_secret", SecretKey)
                            .add("redirect_uri", RedirectUri)
                            .add("code", code)
                            .add("grant_type", "authorization_code").build())
                    .execute().returnContent().asString();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response).get("access_token");
            return node.textValue();
        } catch (Exception ex){
            return null;
        }
    }

}
