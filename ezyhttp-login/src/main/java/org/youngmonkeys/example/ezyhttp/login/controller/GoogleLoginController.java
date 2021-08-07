package org.youngmonkeys.example.ezyhttp.login.controller;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.io.EzyDataConverter;
import com.tvd12.ezyfox.io.EzyDates;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;
import org.youngmonkeys.example.ezyhttp.login.request.LoginWithGoogleRequest;
import org.youngmonkeys.example.ezyhttp.login.response.LoginWithGoogleResponse;
import org.youngmonkeys.example.ezyhttp.login.service.IGoogleLoginService;
import org.youngmonkeys.example.ezyhttp.login.service.IUserInformationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class GoogleLoginController extends BaseController {

    @EzyAutoBind
    private IGoogleLoginService googleLoginService;

    @EzyAutoBind
    private IUserInformationService userInformationService;

    @DoGet("/login-google-success")
    public View index() {
        return View.builder()
                .addVariable("username", "Namnv")
                .template("login-success")
                .build();
    }

    @DoPost("/verify-access-token")
    public Object loginWithGoogle(@RequestBody LoginWithGoogleRequest request) throws Exception {
        LoginWithGoogleResponse response = googleLoginService.loginWithGoogle(request);
        return response;
    }

    @DoGet("/update-infor")
    public View updateUserInfor(@RequestParam String accessToken) {
        UserInformation userInformation = userInformationService.getUserInforByAccessToken(accessToken);
        if (userInformation == null) {
            return View.builder()
                    .template("login-err")
                    .build();
        } else {
            return View.builder()
                    .addVariable("userInformation", userInformation)
                    .template("update-user-infor")
                    .build();
        }
    }

    @DoPost("/save")
    public View saveUserInformation(@RequestParam("id") long id,
                                    @RequestParam("fullName") String fullName,
                                    @RequestParam("birthOfDate") String birthOfDate,
                                    @RequestParam("gender") Integer gender,
                                    @RequestParam("password") String password) {
        Date birthDay = EzyDates.parse(birthOfDate, "yyyy-MM-dd");
        userInformationService.saveUserInformation(id, fullName, birthDay, gender, password);
        return View.builder()
                .addVariable("username", fullName)
                .template("login-success")
                .build();
    }

    @DoGet("/sign-out")
    public View signOut(@RequestParam String accessToken) {
        googleLoginService.signOut(accessToken);
        return View.builder()
                .addVariable("hello", "world")
                .template("home")
                .build();
    }
}
