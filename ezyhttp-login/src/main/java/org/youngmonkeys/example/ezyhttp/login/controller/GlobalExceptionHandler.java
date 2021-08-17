package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyhttp.server.core.annotation.ExceptionHandler;
import com.tvd12.ezyhttp.server.core.annotation.TryCatch;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenExpiredException;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenNotFoundException;

@ExceptionHandler
public class GlobalExceptionHandler {

    @TryCatch({TokenExpiredException.class, TokenNotFoundException.class})
    public Redirect handleAccessTokenException() {
        return Redirect.to("login");
    }
}
