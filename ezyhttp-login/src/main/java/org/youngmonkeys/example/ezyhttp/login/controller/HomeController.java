package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import com.tvd12.ezyhttp.server.core.view.View;

@Controller
public class HomeController {

    @DoGet("/")
    public View index() {
        return View.builder()
            .addVariable("hello", "world")
            .template("home")
            .build();
    }

    @DoGet("/home")
    public Redirect home() {
        return Redirect.to("/");
    }

//    @DoGet("/login-google-success")
//    public View LoginSuccess(){
//        return
//    }

}
