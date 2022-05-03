package com.tvd12.ezyhttp.stresstest.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.ezyhttp.stresstest.request.LoginRequest;

import java.util.UUID;

@Controller("/api")
public class LoginController {

    protected String token;

    public LoginController() {
        this.token = UUID.randomUUID().toString();
    }

    @DoPost("/login")
    public String login(@RequestBody LoginRequest request) {
        return token;
    }

}
