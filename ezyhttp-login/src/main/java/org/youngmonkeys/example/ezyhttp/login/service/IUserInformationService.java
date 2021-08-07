package org.youngmonkeys.example.ezyhttp.login.service;

import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;

import java.util.Date;

public interface IUserInformationService {

    /**
     * Get user information by access token
     * @param accessToken
     * @return User information
     */
    UserInformation getUserInforByAccessToken(String accessToken);

    /**
     * update user information
     * @param id
     * @param fullName
     * @param birthOfDate
     * @param gender
     * @param password
     */
    void saveUserInformation(long id, String fullName, Date birthOfDate, Integer gender, String password);
}
