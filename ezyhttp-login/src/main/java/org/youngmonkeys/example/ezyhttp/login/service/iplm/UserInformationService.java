package org.youngmonkeys.example.ezyhttp.login.service.iplm;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import org.youngmonkeys.example.ezyhttp.login.entity.AccessToken;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.repository.AccessTokenRepository;
import org.youngmonkeys.example.ezyhttp.login.repository.UserInformationRepository;
import org.youngmonkeys.example.ezyhttp.login.service.IUserInformationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@EzySingleton
public class UserInformationService implements IUserInformationService {

    @EzyAutoBind
    private UserInformationRepository userInformationRepository;

    @EzyAutoBind
    private AccessTokenRepository accessTokenRepository;

    @Override
    public UserInformation getUserInforByAccessToken(String accessToken) {
        AccessToken accessTokenObj = accessTokenRepository.findByField("accessToken", accessToken);
        if (accessTokenObj != null) {
            UserInformation userInformation = userInformationRepository.findById(accessTokenObj.getUserId());
            return userInformation;
        }
        return null;
    }

    @Override
    public void saveUserInformation(long id, String fullName, Date birthOfDate, Integer gender, String password) {
        UserInformation userInformation = userInformationRepository.findById(id);
        userInformation.setFullName(fullName);
        userInformation.setBirthOfDate(birthOfDate);
        userInformation.setGender(gender);
        userInformation.setPassword(EzySHA256.cryptUtfToLowercase(password));
        userInformation.setUpdateTime(LocalDateTime.now());
        userInformationRepository.save(userInformation);
    }
}
