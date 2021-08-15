package org.youngmonkeys.example.ezyhttp.login.controller;

import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.io.EzyDates;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import org.hibernate.sql.Update;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.request.LoginRequest;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleService;
import org.youngmonkeys.example.ezyhttp.login.service.IUserInformationService;

import java.util.Date;
import java.util.UUID;

@Controller
public class LoginController {

    @EzyAutoBind
    private IUserInformationService userInformationService;

    @EzyAutoBind
    private IGoogleService googleService;

    @DoPost("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        UserInformation userInformation = userInformationService.getUserInfoByEmail(loginRequest.getEmail());
        String passHash = EzySHA256.cryptUtfToLowercase(loginRequest.getPassword());
        if (userInformation != null && passHash.equals(userInformation.getPassword())) {
            String accessToken = EzySHA256.cryptUtfToLowercase(userInformation.getId() + UUID.randomUUID().toString());
            userInformationService.saveAccessToken(userInformation.getId(), accessToken);
            return Redirect.to("home?accessToken=" + accessToken);
        } else {
            return View.builder()
                    .template("login-err")
                    .build();
        }
    }

    @DoGet("/login-callback")
    public Object loginWithGoogle(@RequestParam String code) throws Exception {
        String accessToken = googleService.getAccessToken(code);
        if (accessToken == null) {
            return Redirect.to("login-err");
        }

        Userinfo userinfoGoogle = googleService.getUserInfoByAccessToken(accessToken);

        UserInformation userInformation = userInformationService.getUserInfoByEmail(userinfoGoogle.getEmail());

        if (userInformation == null) {
            userInformationService.saveUserInfoGoogle(userinfoGoogle, accessToken);
            return Redirect.to("get-user-info?accessToken=" + accessToken);
        } else {
            userInformationService.saveAccessToken(userInformation.getId(), accessToken);
            return Redirect.to("home?accessToken=" + accessToken);
        }
    }

    @DoGet("/get-user-info")
    public View getUserInfo(@RequestParam String accessToken) {
        UserInformation userInformation = userInformationService.getUserInfoByAccessToken(accessToken);
        return View.builder()
                .addVariable("userInformation", userInformation)
                .addVariable("accessToken", accessToken)
                .template("update-user-info")
                .build();
    }

    @DoPost("/save")
    public Object saveUserInformation(@RequestBody UpdateUserRequest request) {
        if (userInformationService.saveUserInformation(request)) {
            return Redirect.to("home?accessToken=" + request.getAccessToken());
        }
        return Redirect.to("/");
    }

    @DoGet("logout")
    public Object logout(@RequestParam String accessToken) {
        userInformationService.logout(accessToken);
        return Redirect.to("/");
    }
}
