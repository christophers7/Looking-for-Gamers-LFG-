package presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.LoginRequest;
import presentation.models.UpdateUserProfileRequest;
import repository.DAO.implementation.UserCredentialsDao;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.UserProfile;
import service.login.classes.LoginService;
import service.profile.classes.ProfileService;
import utility.JWTInfo;
import utility.JWTUtility;

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
