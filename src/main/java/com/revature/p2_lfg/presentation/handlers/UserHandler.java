package com.revature.p2_lfg.presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.DAO.implementation.UserCredentialsDao;
import com.revature.p2_lfg.repository.DAO.implementation.UserProfileDao;
import com.revature.p2_lfg.repository.entities.UserProfile;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;

public class UserHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");


    private ProfileService profileService;
    private LoginService loginService;

    public UserHandler() {
        this.loginService = new LoginService(new UserCredentialsDao());
        this.profileService = new ProfileService(new UserProfileDao());
    }

    public Handler updateProfile = ctx -> {
        dLog.debug("Attempting to update user profile: "  + ctx.body());
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            try{
                UserProfile storedProfile = profileService.getUserProfile(parsedJWT.getUserId());
                ctx.json(profileService.updateProfileWithRequest(ctx.bodyAsClass(UpdateUserProfileRequest.class), storedProfile));
                ctx.status(200);
            }catch(Exception e){
                dLog.error(e.getMessage(), e);
            }
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };

    public Handler getUserProfile = ctx -> {
        dLog.debug("Attempting to get user profile: " + ctx.body());
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            try{
                ctx.json(profileService.getUserProfile(parsedJWT.getUserId()));
                ctx.status(200);
            }catch(Exception e){
                dLog.error(e.getMessage(), e);
            }
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };
}
