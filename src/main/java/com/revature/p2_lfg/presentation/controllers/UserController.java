package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("userController")
@RequestMapping("/user")
public class UserController {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private ProfileService profileService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/update")
    public ProfileResponse updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateUserProfileRequest profile){
        dLog.debug("Attempting to update user profile: "  + profile);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return profileService.updateProfileWithRequest(profile, profileService.getUserProfile(parsedJWT.getUserId()));
        else return null;
    }

    @GetMapping("/profile")
    public ProfileResponse getUserProfile(@RequestHeader("Authorization") String token){
        dLog.debug("Attempting to get user profile");
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        dLog.debug("JWT TOken : " + parsedJWT);
        if(parsedJWT != null) return profileService.convertUserProfileToProfileResponse(profileService.getUserProfile(parsedJWT.getUserId()));
        else return null;
    }
}
