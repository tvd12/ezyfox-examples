package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.entity.User;
import org.youngmonkeys.example.ezyhttp.login.entity.UserStatus;
import org.youngmonkeys.example.ezyhttp.login.exception.UserNotFoundException;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IUserService;

@Controller
public class UserController {

    @EzyAutoBind
    private IUserService userService;

    @DoGet("/user/update")
    public View userUpdateGet(@UserId long userId) {
        User user = userService.getUserById(userId);
        return View.builder()
                .addVariable("user", user)
                .addVariable("accountType", user.getAccountType().toString())
                .template("user-update")
                .build();
    }

    @DoPost("/user/save")
    public Object userSavePost(
        @UserId long userId,
        @RequestBody UpdateUserRequest request
    ) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("user with id: " + userId + " not found");
        }
        user.setEmail(request.getEmail());
        user.setFullName(request.getFirstName() + " " + request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setStatus(UserStatus.UPDATED);
        user.setPassword(EzySHA256.cryptUtfToLowercase(request.getPassword()));
        userService.saveUser(user);
        return Redirect.builder()
            .uri("/home")
            .build();
    }
}
