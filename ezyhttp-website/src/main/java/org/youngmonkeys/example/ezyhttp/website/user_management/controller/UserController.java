package org.youngmonkeys.example.ezyhttp.website.user_management.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import lombok.Setter;
import org.youngmonkeys.example.ezyhttp.website.user_management.entity.User;
import org.youngmonkeys.example.ezyhttp.website.user_management.service.UserService;

@Setter
@Controller("api/v1/users")
public class UserController {

    @EzyAutoBind
    protected UserService userService;


    @DoPost("/add")
    public Redirect addUser(@RequestBody User user) {
        userService.addUser(user);
        return Redirect.builder().uri("/users/home")
            .addAttribute("username",user.getUsername())
            .build();
    }


}
