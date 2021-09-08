package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.example.ezyhttp.login.entity.UserData;
import org.youngmonkeys.example.ezyhttp.login.repository.UserDataRepository;
import org.youngmonkeys.example.ezyhttp.login.service.IUserDataService;

@EzySingleton
public class UserDataService implements IUserDataService {

    private static final String GOOGLE_TOKEN_KEY = "googleToken";

    private static final String FACEBOOK_TOKEN_KEY = "facebookToken";

    @EzyAutoBind
    private UserDataRepository userDataRepository;

    @Override
    public void saveGoogleToken(long userId, String googleToken) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userData.setKey(GOOGLE_TOKEN_KEY);
        userData.setValue(googleToken);
        userDataRepository.save(userData);
    }

    @Override
    public void saveFacebookToken(long userId, String facebookToken) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userData.setKey(FACEBOOK_TOKEN_KEY);
        userData.setValue(facebookToken);
        userDataRepository.save(userData);
    }
}
