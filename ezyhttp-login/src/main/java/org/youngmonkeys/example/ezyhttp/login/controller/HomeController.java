package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.entity.User;
import org.youngmonkeys.example.ezyhttp.login.entity.UserStatus;
import org.youngmonkeys.example.ezyhttp.login.service.impl.UserService;

@Controller
public class HomeController {

    @EzyAutoBind
    private UserService loginService;

    @DoGet("/")
    public View index() {
        return View.builder()
            .template("login")
            .build();
    }

    @DoGet("/home")
    public Object home(@UserId long userId, @RequestParam String accessToken) {
        User user = loginService.getUserById(userId);
        if (user == null) {
            return Redirect.to("/");
        }
        if (user.getStatus() == UserStatus.REGISTER) {
            return Redirect.to("/user/update?accessToken=" + accessToken);
        }
        return View.builder()
            .addVariable("email", user.getEmail())
            .addVariable("accessToken", accessToken)
            .template("home")
            .build();
    }
}
