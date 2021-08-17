package org.youngmonkeys.example.ezyhttp.login.interceptor;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.collect.Sets;
import com.tvd12.ezyhttp.core.annotation.Interceptor;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.controller.HomeController;
import org.youngmonkeys.example.ezyhttp.login.controller.LoginController;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IAuthenticationService;

import java.lang.reflect.Method;
import java.util.Set;

@Interceptor
public class PermissionInterceptor implements RequestInterceptor {

    private final Set<Method> authorizedMethods;

    @EzyAutoBind
    private IAuthenticationService authenticationService;

    public PermissionInterceptor() {
        try {
            authorizedMethods = Sets.newHashSet(
                    HomeController.class.getDeclaredMethod("home", long.class, String.class),
                    LoginController.class.getDeclaredMethod("getUserInfo", long.class, String.class),
                    LoginController.class.getDeclaredMethod("saveUserInformation", UpdateUserRequest.class)
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean preHandle(RequestArguments arguments, Method handler) throws Exception {
        if (!authorizedMethods.contains(handler)) {
            return true;
        }
        String accessToken = arguments.getParameter("accessToken");
        if (accessToken == null) {
            accessToken = arguments.getHeader("accessToken");
        }

        long userId = authenticationService.verifyAccessToken(accessToken);
        arguments.setArgument(UserId.class, userId);

        return RequestInterceptor.super.preHandle(arguments, handler);
    }



}
