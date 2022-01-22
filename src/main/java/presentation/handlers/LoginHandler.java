package presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.LoginRequest;
import repository.DAO.implementation.UserCredentialsDao;
import service.login.classes.LoginService;
import service.profile.classes.ProfileService;

public class LoginHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private LoginService loginService;
    private ProfileService profileService;

    public LoginHandler() {
        this.loginService = new LoginService(new UserCredentialsDao());
        this.profileService = new ProfileService();
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

}
