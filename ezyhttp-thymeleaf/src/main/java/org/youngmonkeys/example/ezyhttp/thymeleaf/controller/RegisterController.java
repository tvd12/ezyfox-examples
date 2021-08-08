package org.youngmonkeys.example.ezyhttp.thymeleaf.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.ezyhttp.thymeleaf.entity.User;
import org.youngmonkeys.example.ezyhttp.thymeleaf.request.RegisterRequest;
import org.youngmonkeys.example.ezyhttp.thymeleaf.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class RegisterController {
    private final UserService userService;

    @DoGet("/register")
    public View registerGet() {
        return View.of("register");
    }

    @DoPost("/register")
    public Redirect registerPost(@RequestBody RegisterRequest request) {
        final User user = new User(
            request.getUsername(),
            request.getPassword(),
            request.getBirthOfDate(),
            LocalDate.now(),
            LocalDateTime.now()
        );
        userService.addUser(user);
        return Redirect.to("/user/" + request.getUsername());
    }
}
