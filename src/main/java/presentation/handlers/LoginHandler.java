package presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.LoginRequest;
import presentation.models.NewUserCredentialsRequest;
import repository.DAO.implementation.UserCredentialsDao;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.UserCredential;
import service.login.classes.LoginService;
import service.profile.classes.ProfileService;

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
                ctx.json(profileService.getProfileResponse(loginService.getUserCredential(loginRequest)));
            }catch(Exception e){
                dLog.error(e.getMessage(), e);
                ctx.status(406);
            }
    };

    public Handler newLogin = ctx -> {
        dLog.debug("Creating new user login: " + ctx.body());
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
            ctx.status(406);
        }
    };

}
