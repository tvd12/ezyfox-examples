package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.server.core.annotation.ExceptionHandler;
import com.tvd12.ezyhttp.server.core.annotation.TryCatch;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenExpiredException;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenNotFoundException;
import org.youngmonkeys.example.ezyhttp.login.exception.UserNotFoundException;

@ExceptionHandler
public class GlobalExceptionHandler extends EzyLoggable {

    @TryCatch({TokenExpiredException.class, TokenNotFoundException.class})
    public Redirect handleAccessTokenException(RuntimeException e) {
        logger.info("token error", e);
        return Redirect.to("/login");
    }

    @TryCatch(UserNotFoundException.class)
    public Redirect handleUserNotFoundException(UserNotFoundException e) {
        logger.info("user not found", e);
        return Redirect.to("/login?from_scratch=true");
    }
}
