package org.youngmonkeys.example.ezyhttp.login.service;


import com.restfb.types.User;

/**
 * Interface of facebook login service
 */
public interface IFacebookService {
    User getUserInfoByAccessToken(String accessToken);

    String getAccessToken(String code);
}
