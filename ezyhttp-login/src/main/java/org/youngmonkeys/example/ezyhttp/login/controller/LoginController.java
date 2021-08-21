package org.youngmonkeys.example.ezyhttp.login.controller;

import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.example.ezyhttp.login.entity.User;
import org.youngmonkeys.example.ezyhttp.login.entity.UserStatus;
import org.youngmonkeys.example.ezyhttp.login.request.LoginRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IAuthenticationService;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleService;
import org.youngmonkeys.example.ezyhttp.login.service.IUserDataService;
import org.youngmonkeys.example.ezyhttp.login.service.IUserService;

@Controller
public class LoginController {

    @EzyAutoBind
    private IUserService userService;

    @EzyAutoBind
    private IUserDataService userDataService;

    @EzyAutoBind
    private IGoogleService googleService;

    @EzyAutoBind
    private IAuthenticationService authenticationService;

    @DoGet("/login")
    public View loginGet() {
        return View.builder()
            .template("login")
            .build();
    }

    @DoPost("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserInfoByEmail(loginRequest.getEmail());
        if (user != null) {
            String hashPassword = EzySHA256.cryptUtfToLowercase(loginRequest.getPassword());
             if (hashPassword.equals(user.getPassword())) {
                 String accessToken = authenticationService.generateAccessToken(user.getId());
                 return Redirect.builder()
                     .addCookie("accessToken", accessToken)
                     .uri("/home")
                     .build();
             }
        }
        return Redirect.to("/login-error");
    }

    @DoGet("/login-error")
    public View loginErrorGet() {
        return View.builder()
            .template("login-error")
            .build();
    }

    @DoGet("/login-callback")
    public Object loginWithGoogle(@RequestParam String code) {
        String googleAccessToken = googleService.getAccessToken(code);
        if (googleAccessToken == null) {
            return Redirect.to("login-error");
        }
        Userinfo googleUserInfo = googleService.getUserInfoByAccessToken(googleAccessToken);
        if (googleUserInfo == null) {
            return Redirect.to("login-error");
        }
        boolean userExisted = true;
        User user = userService.getUserInfoByEmail(googleUserInfo.getEmail());
        if (user == null) {
            userExisted = false;
            user = userService.saveGoogleUserInfo(googleUserInfo);
            userDataService.saveGoogleToken(user.getId(), googleAccessToken);
        }
        String accessToken = authenticationService.generateAccessToken(user.getId());
        return userExisted && user.getStatus() == UserStatus.UPDATED
            ? Redirect.builder()
                .uri("/home")
                .addCookie("accessToken", accessToken)
                .build()
            : Redirect.builder()
                .uri("/user/update")
                .addCookie("accessToken", accessToken)
                .build();
    }

    @DoPost("/logout")
    public Object logout(@RequestCookie("accessToken") String accessToken) {
        authenticationService.removeAccessToken(accessToken);
        return Redirect.to("/");
    }
}
