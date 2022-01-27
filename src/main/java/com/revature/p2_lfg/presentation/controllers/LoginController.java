package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean newLogin(@RequestBody NewUserCredentialsRequest newUser){
        UserCredential userCredential = loginService.newAccount(newUser);
        if(userCredential != null)
            if(profileService.newUserProfile(userCredential, newUser.getEmail()) != null) {
                iLog.info("Successful creation of an account: " + newUser + "\n" + userCredential);
                return true;
            }
        dLog.debug("Failed to create new login" + newUser);
        return false;
    }

    @PatchMapping("/update-password")
    public boolean updatePassword(@RequestHeader("Authorization") String token , @RequestBody UpdatePasswordRequest newPassword){
        dLog.debug("Attempting to update password: " + newPassword);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return loginService.updateUserCredentialPassword(newPassword, parsedJWT);
        else return false;

    }

    @PatchMapping("/update-username")
    public boolean updateUsername(@RequestHeader("Authorization") String token, @RequestBody UpdateUsernameRequest newUsername){
        dLog.debug("Attempting to update user login: " + newUsername);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return loginService.updateUserCredentialUsername(newUsername, parsedJWT);
        else return false;
    }

    @PatchMapping("/reset-password")
    public boolean resetPassword(@RequestBody ResetPasswordRequest resetPassword){
        dLog.debug("Attempting to reset password: " + resetPassword);
        return loginService.resetPassword(resetPassword);
    }
}
