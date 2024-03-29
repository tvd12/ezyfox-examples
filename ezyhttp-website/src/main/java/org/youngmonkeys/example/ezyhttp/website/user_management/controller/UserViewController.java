package org.youngmonkeys.example.ezyhttp.website.user_management.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.Setter;
import org.youngmonkeys.example.ezyhttp.website.user_management.entity.User;
import org.youngmonkeys.example.ezyhttp.website.user_management.service.UserService;

@Setter
@Controller("users")
public class UserViewController {

    @EzyAutoBind
    protected UserService userService;

    @DoGet("/add")
    public View addUserView() {
        return View.builder()
                .template("user-add")
                .build();
    }

    @DoGet("/search")
    public View searchUserView() {
        return View.builder()
                .template("user-search")
                .build();
    }


    @DoGet("/home")
    public Object home(RequestArguments arguments) {
        String username = arguments.getRedirectionAttribute("username");
        return View.builder()
                .addVariable("username", username)
                .template("home")
                .build();
    }

    @DoGet("/{username}")
    public View getUser(@PathVariable("username") String username) {
        User user = userService.getUser(username);
        String msg = user != null ? "User " + username + " with info: " + user : "User " + username + " not found";
        return View.builder()
                .addVariable("message", msg)
                .template("user-info")
                .build();
    }

}
