package org.youngmonkeys.example.ezyhttp.login.interceptor;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.annotation.Interceptor;
import com.tvd12.ezyhttp.core.constant.HttpMethod;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.manager.RequestURIManager;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.ezyhttp.login.annotation.UserId;
import org.youngmonkeys.example.ezyhttp.login.exception.TokenNotFoundException;
import org.youngmonkeys.example.ezyhttp.login.service.IAuthenticationService;

import java.lang.reflect.Method;

@Interceptor
@AllArgsConstructor
public class PermissionInterceptor extends EzyLoggable implements RequestInterceptor {

    private final RequestURIManager requestUriManager;
    private final IAuthenticationService authenticationService;

    @Override
    public boolean preHandle(RequestArguments arguments, Method handler) throws Exception {
        logger.info("request uri: {}", arguments.getRequest().getRequestURI());
        HttpMethod method = arguments.getMethod();
        String uriTemplate = arguments.getUriTemplate();
        if (requestUriManager.isAuthenticatedURI(method, uriTemplate)) {
            return true;
        }
        String accessToken = arguments.getParameter("accessToken");
        if (accessToken == null) {
            accessToken = arguments.getHeader("accessToken");
        }
        if (accessToken == null) {
            accessToken = arguments.getCookieValue("accessToken");
        }
        if (accessToken == null) {
            throw new TokenNotFoundException("Can not get accessToken from cookie");
        }
        long userId = authenticationService.verifyAccessToken(accessToken);
        arguments.setArgument(UserId.class, userId);

        return RequestInterceptor.super.preHandle(arguments, handler);
    }
}
