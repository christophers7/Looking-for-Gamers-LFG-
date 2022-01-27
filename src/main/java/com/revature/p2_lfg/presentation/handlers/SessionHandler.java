package com.revature.p2_lfg.presentation.handlers;

import com.revature.p2_lfg.presentation.models.session.CreateGroupSessionRequest;
import com.revature.p2_lfg.service.session.classes.SessionService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sessionHandler")
public class SessionHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private SessionService sessionService;

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
