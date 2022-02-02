package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTUtility;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController("loginController")
@RequestMapping("/login")
public class LoginController {


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
        return profileService.newUserProfile(loginService.newAccount(newUser), newUser.getEmail());
    }

    @PutMapping("/update")
    public ProfileResponse updateCredentials(@NonNull @RequestHeader("Authorization") String token , @NonNull @RequestBody UpdatePasswordRequest newCredentials){
        return loginService.updateCredentials(newCredentials, Objects.requireNonNull(JWTUtility.verifyUser(token)));
    }

    @PutMapping("/update-password")
    public ProfileResponse updatePassword(@NonNull @RequestHeader("Authorization") String token , @NonNull @RequestBody UpdatePasswordRequest newPassword){
        return loginService.updateUserCredentialPassword(newPassword, Objects.requireNonNull(JWTUtility.verifyUser(token)));
    }

    @PutMapping("/update-username")
    public ProfileResponse updateUsername(@NonNull @RequestHeader("Authorization") String token,@NonNull @RequestBody UpdateUsernameRequest newUsername){
        return loginService.updateUserCredentialUsername(newUsername, Objects.requireNonNull(JWTUtility.verifyUser(token)));
    }

    @PutMapping("/reset-password")
    public ProfileResponse resetPassword(@NonNull @RequestBody ResetPasswordRequest resetPassword){
        return loginService.resetPassword(resetPassword);
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNonNullException(){
        return "User not found";
    }
}
