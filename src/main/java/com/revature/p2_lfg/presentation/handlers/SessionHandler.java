package presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.session.CreateGroupSessionRequest;
import repository.DAO.implementation.SessionDao;
import repository.DAO.implementation.SessionDetailsDao;
import service.session.classes.SessionService;
import utility.JWTInfo;
import utility.JWTUtility;

public class SessionHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private SessionService sessionService;

    public SessionHandler() {
        this.sessionService = new SessionService(new SessionDetailsDao(), new SessionDao());
    }

    public Handler createGroupSession = ctx -> {
        dLog.debug("Creating a group session: " + ctx.body());
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            try{
                CreateGroupSessionRequest createGroup = ctx.bodyAsClass(CreateGroupSessionRequest.class);
                ctx.json(sessionService.createGroupSession(createGroup, parsedJWT));
                ctx.status(200);
            }catch(Exception e){
                dLog.error(e.getMessage(), e);
            }
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };
}
