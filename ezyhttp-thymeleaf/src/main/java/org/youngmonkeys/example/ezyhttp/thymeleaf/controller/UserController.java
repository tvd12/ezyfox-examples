package org.youngmonkeys.example.ezyhttp.thymeleaf.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.ezyhttp.thymeleaf.entity.User;
import org.youngmonkeys.example.ezyhttp.thymeleaf.service.UserService;

@AllArgsConstructor
@Controller("/user")
public class UserController {
    private final UserService userService;

    @DoGet("/{username}")
    public Object getUser(@PathVariable String username) {
        final User user = userService.getUser(username);
        if (user == null) {
            return Redirect.to("/login");
        }
        return View.builder()
            .template("user")
            .addVariable("user", user)
            .build();
    }
}
