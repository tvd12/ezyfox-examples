package org.youngmonkeys.example.ezyhttp.website.user_management.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.core.exception.HttpConflictException;
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.Setter;
import org.youngmonkeys.example.ezyhttp.website.user_management.entity.User;
import org.youngmonkeys.example.ezyhttp.website.user_management.service.UserService;

@Setter
@Controller("api/v1/users")
public class UserController {

    @EzyAutoBind
    protected UserService userService;

    @DoGet("/add")
    public Object addUserView() {
        return View.builder()
                .template("user-add")
                .build();
    }

    @DoGet("/search")
    public Object searchUserView() {
        return View.builder()
                .template("user-search")
                .build();
    }

    @DoPost("/add")
    public Object addUser(@RequestBody User user) {
        User existed = userService.addUser(user);
        return View.builder()
                .addVariable("username", user.getUsername())
                .template("home")
                .build();
    }

    @DoGet("/{username}")
    public Object getUser(@PathVariable("username") String username) {
        User user = userService.getUser(username);
        String msg = user != null ? "User " + username + " with info: " + user : "User " + username + " not found";
        return View.builder()
                .addVariable("message", msg)
                .template("user-info")
                .build();
    }

}
