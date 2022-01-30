package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTUtility;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController("loginController")
@RequestMapping("/login")
public class LoginController {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private LoginService loginService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/check")
    public ProfileResponse checkLogin(@RequestBody LoginRequest loginRequest){
        return profileService.getProfileResponse(loginService.getUserCredentialFromLogin(loginRequest));
    }

    @PostMapping("/new")
    public ProfileResponse newLogin(@NonNull @RequestBody NewUserCredentialsRequest newUser) {
        UserCredential userCredential = loginService.newAccount(newUser);
        return profileService.newUserProfile(userCredential, newUser.getEmail());
    }

    @PutMapping("/update-password")
    public boolean updatePassword(@NonNull @RequestHeader("Authorization") String token , @NonNull @RequestBody UpdatePasswordRequest newPassword){
        dLog.debug("Attempting to update password: " + newPassword);
        return loginService.updateUserCredentialPassword(newPassword, Objects.requireNonNull(JWTUtility.verifyUser(token)));
    }

    @PutMapping("/update-username")
    public boolean updateUsername(@NonNull @RequestHeader("Authorization") String token,@NonNull @RequestBody UpdateUsernameRequest newUsername){
        dLog.debug("Attempting to update user login: " + newUsername);
        return loginService.updateUserCredentialUsername(newUsername, Objects.requireNonNull(JWTUtility.verifyUser(token)));
    }

    @PutMapping("/reset-password")
    public boolean resetPassword(@NonNull @RequestBody ResetPasswordRequest resetPassword){
        dLog.debug("Attempting to reset password: " + resetPassword);
        return loginService.resetPassword(resetPassword);
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNonNullException(){
        return "Null value error";
    }
}
