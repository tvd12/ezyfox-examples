package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.example.ezyhttp.login.entity.UserData;
import org.youngmonkeys.example.ezyhttp.login.repository.UserDataRepository;
import org.youngmonkeys.example.ezyhttp.login.service.IUserDataService;

@EzySingleton
public class UserDataService implements IUserDataService {

    private static final String GOOGLE_TOKEN_KEY = "googleToken";

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
}
