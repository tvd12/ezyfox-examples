package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyhttp.server.core.annotation.Authenticated;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.entity.User;
import org.youngmonkeys.example.ezyhttp.login.entity.UserStatus;
import org.youngmonkeys.example.ezyhttp.login.service.impl.UserService;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService loginService;

    @DoGet("/")
    public Object index() {
        return Redirect.to("/home");
    }

    @Authenticated
    @DoGet("/home")
    public Object home(@UserId long userId) {
        User user = loginService.getUserById(userId);
        if (user == null) {
            return Redirect.to("/login");
        }
        if (user.getStatus() == UserStatus.REGISTER) {
            return Redirect.to("/user/update");
        }
        return View.builder()
            .addVariable("email", user.getEmail())
            .template("home")
            .build();
    }
}
