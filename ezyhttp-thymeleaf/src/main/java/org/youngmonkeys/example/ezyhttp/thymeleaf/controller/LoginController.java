package org.youngmonkeys.example.ezyhttp.thymeleaf.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.ezyhttp.thymeleaf.entity.User;
import org.youngmonkeys.example.ezyhttp.thymeleaf.service.UserService;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @DoGet("/login")
    public View loginGet() {
        return View.of("login");
    }

    @DoPost("/login")
    public Redirect loginPost(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("lastLoginDate") LocalDate lastLoginDate
    ) {
        final User user = userService.getUser(username);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                return Redirect.to("/user/" + username);
            }
            else {
                return Redirect.to("/login");
            }
        }
        else {
            return Redirect.to("/register");
        }
    }
}
