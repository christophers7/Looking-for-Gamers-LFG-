package com.revature.p2_lfg.presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.repository.DAO.implementation.UserCredentialsDao;
import com.revature.p2_lfg.repository.DAO.implementation.UserProfileDao;
import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;

public class LoginHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private LoginService loginService;
    private ProfileService profileService;

    public LoginHandler() {
        this.loginService = new LoginService(new UserCredentialsDao());
        this.profileService = new ProfileService(new UserProfileDao());
    }

    public Handler checkLogin = ctx -> {
            dLog.debug("Checking user login: " + ctx.body());
            try{
                LoginRequest loginRequest = ctx.bodyAsClass(LoginRequest.class);
                ctx.json(profileService.getProfileResponse(loginService.getUserCredentialFromLogin(loginRequest)));
            }catch(Exception e){
                dLog.error(e.getMessage(), e);
                ctx.status(406);
            }
    };

    public Handler newLogin = ctx -> {
        dLog.debug("Attempting to create a new user login: " + ctx.body());
        try{
            NewUserCredentialsRequest newUser = ctx.bodyAsClass(NewUserCredentialsRequest.class);
            UserCredential userCredential = loginService.newAccount(newUser);
            if(userCredential != null)
                if(profileService.newUserProfile(userCredential, newUser.getEmail()) != null){
                    iLog.info("Successful creation of an account: " + newUser + "\n" + userCredential);
                    ctx.json(true);
                    ctx.status(201);
                }
                else ctx.status(406);
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };

    public Handler updateUsername = ctx -> {
        dLog.debug("Attempting to update user login: " + ctx.body());
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            ctx.json(loginService.updateUserCredentialUsername(ctx.bodyAsClass(UpdateUsernameRequest.class), parsedJWT));
            ctx.status(205);
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };

    public Handler resetPassword = ctx -> {
        dLog.debug("Attempting to reset password: " + ctx.body());
        try{
            ctx.json(loginService.resetPassword(ctx.bodyAsClass(ResetPasswordRequest.class)));
            ctx.status(205);
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };

    public Handler updatePassword = ctx -> {
        dLog.debug("Attempting to update password: " + ctx.body());
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            ctx.json(loginService.updateUserCredentialPassword(ctx.bodyAsClass(UpdatePasswordRequest.class), parsedJWT));
            ctx.status(205);
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };

}
