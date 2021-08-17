package org.youngmonkeys.example.ezyhttp.login.controller;

import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.request.LoginRequest;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleService;
import org.youngmonkeys.example.ezyhttp.login.service.ILoginService;

import java.util.UUID;

@Controller
public class LoginController {

    @EzyAutoBind
    private ILoginService loginService;

    @EzyAutoBind
    private IGoogleService googleService;

    @DoPost("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        UserInformation userInformation = loginService.getUserInfoByEmail(loginRequest.getEmail());
        String hashPassword = EzySHA256.cryptUtfToLowercase(loginRequest.getPassword());
        if (userInformation != null && hashPassword.equals(userInformation.getPassword())) {
            String accessToken = EzySHA256.cryptUtfToLowercase(userInformation.getId() + UUID.randomUUID().toString());
            loginService.saveAccessToken(userInformation.getId(), accessToken);
            return Redirect.to("home?accessToken=" + accessToken);
        } else {
            return View.builder()
                    .template("login-error")
                    .build();
        }
    }

    @DoGet("/login-callback")
    public Object loginWithGoogle(@RequestParam String code) throws Exception {
        String accessToken = googleService.getAccessToken(code);
        if (accessToken == null) {
            return Redirect.to("login-error");
        }

        Userinfo userinfoGoogle = googleService.getUserInfoByAccessToken(accessToken);

        UserInformation userInformation = loginService.getUserInfoByEmail(userinfoGoogle.getEmail());

        if (userInformation == null) {
            loginService.saveUserInfoGoogle(userinfoGoogle, accessToken);
            return Redirect.to("get-user-info?accessToken=" + accessToken);
        } else {
            loginService.saveAccessToken(userInformation.getId(), accessToken);
            return Redirect.to("home?accessToken=" + accessToken);
        }
    }

    @DoGet("/get-user-info")
    public View getUserInfo(@UserId long userId, @RequestParam String accessToken) {
        UserInformation userInformation = loginService.getUserById(userId);
        return View.builder()
                .addVariable("userInformation", userInformation)
                .addVariable("accessToken", accessToken)
                .template("update-user-info")
                .build();
    }

    @DoPost("/save")
    public Object saveUserInformation(@RequestBody UpdateUserRequest request) {
        if (loginService.saveUserInformation(request)) {
            return Redirect.to("home?accessToken=" + request.getAccessToken());
        }
        return Redirect.to("/");
    }

    @DoGet("logout")
    public Object logout(@RequestParam String accessToken) {
        loginService.logout(accessToken);
        return Redirect.to("/");
    }
}
