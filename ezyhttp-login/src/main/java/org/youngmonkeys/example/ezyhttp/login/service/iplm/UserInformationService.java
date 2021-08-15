package org.youngmonkeys.example.ezyhttp.login.service.iplm;

import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.annotation.EzyProperty;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import org.youngmonkeys.example.ezyhttp.login.entity.AccessToken;
import org.youngmonkeys.example.ezyhttp.login.entity.AccountType;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.repository.AccessTokenRepository;
import org.youngmonkeys.example.ezyhttp.login.repository.UserInformationRepository;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IUserInformationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@EzySingleton
public class UserInformationService implements IUserInformationService {

    @EzyProperty("access_token.expires_in")
    private int ExpireIn;

    @EzyAutoBind
    private UserInformationRepository userInformationRepository;

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    public UserInformation getUserInfoByAccessToken(String accessToken) {
        AccessToken accessTokenObj = accessTokenRepository.findByField("accessToken", accessToken);
        if (accessTokenObj != null && accessTokenObj.getExpireIn().compareTo(LocalDateTime.now()) == 1) {
            UserInformation userInformation = userInformationRepository.findById(accessTokenObj.getUserId());
            return userInformation;
        }
        return null;
    }

    @Override
    public UserInformation getUserInfoByEmail(String email) {
        UserInformation userInformation = userInformationRepository.findByField("email", email);
        return userInformation;
    }

    @Override
    public void saveUserInfoGoogle(Userinfo userinfo, String accessTokenGoogle) {
        // create new user information
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail(userinfo.getEmail());
        userInformation.setFullName(userinfo.getName());
        userInformation.setLastName(userinfo.getFamilyName());
        userInformation.setFirstName(userinfo.getGivenName());
        userInformation.setAvatarURL(userinfo.getPicture());
        userInformation.setAccountType(AccountType.GOOGLE);
        userInformation.setCreateTime(LocalDateTime.now());
        userInformation.setUpdateTime(LocalDateTime.now());
        userInformation.setVersion(1);
        userInformation.setDeleted(false);
        userInformationRepository.save(userInformation);

        // create new access token
        saveAccessToken(userInformation.getId(), accessTokenGoogle);
    }

    @Override
    public boolean saveUserInformation(UpdateUserRequest request) {
        AccessToken accessTokenObj = accessTokenRepository.findByField("accessToken", request.getAccessToken());
        if (accessTokenObj != null && accessTokenObj.getExpireIn().compareTo(LocalDateTime.now()) == 1) {
            UserInformation userInformation = userInformationRepository.findById(request.getId());
            userInformation.setFullName(request.getFirstName() + " " + request.getLastName());
            userInformation.setFirstName(request.getFirstName());
            userInformation.setLastName(request.getLastName());
            userInformation.setPassword(EzySHA256.cryptUtfToLowercase(request.getPassword()));
            userInformation.setUpdateTime(LocalDateTime.now());
            userInformationRepository.save(userInformation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveAccessToken(long userId, String accessTokenGoogle) {
        // create new access token
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(accessTokenGoogle);
        accessToken.setExpireAt(LocalDateTime.now());
        accessToken.setExpireIn(LocalDateTime.now().plusSeconds(ExpireIn));
        accessToken.setFirstIssueAt(LocalDateTime.now());
        accessToken.setUpdateTime(LocalDateTime.now());
        accessToken.setUserId(userId);
        accessToken.setCreateTime(LocalDateTime.now());
        accessToken.setVersion(1);
        accessTokenRepository.save(accessToken);
    }

    @Override
    public void logout(String accessToken) {
        accessTokenRepository.delete(accessToken);
    }
}
