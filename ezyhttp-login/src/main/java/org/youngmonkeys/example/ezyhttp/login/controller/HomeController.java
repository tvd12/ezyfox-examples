package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.service.impl.LoginService;

@Controller
public class HomeController {

    @EzyAutoBind
    private LoginService loginService;

    @DoGet("/")
    public View index() {
        return View.builder()
            .template("login")
            .build();
    }

    @DoGet("/login")
    public View login() {
        return View.builder()
                .template("login")
                .build();
    }

    @DoGet("/home")
    public Object home(@UserId long userId, @RequestParam String accessToken) {
        UserInformation userInformation = loginService.getUserById(userId);
        if (userInformation != null) {
            return View.builder()
                    .addVariable("email", userInformation.getEmail())
                    .addVariable("accessToken", accessToken)
                    .template("home")
                    .build();
        } else {
            return Redirect.to("/");
        }
    }
}
