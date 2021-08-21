package org.youngmonkeys.example.ezyhttp.thymeleaf.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.Redirect;

@Controller
public class HomeController {

    @DoGet("/")
    public Redirect home() {
        return Redirect.to("/login");
    }
}
