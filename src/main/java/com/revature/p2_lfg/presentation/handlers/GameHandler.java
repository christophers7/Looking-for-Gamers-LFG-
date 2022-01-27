package com.revature.p2_lfg.presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.repository.DAO.implementation.GamesDao;
import com.revature.p2_lfg.repository.DAO.implementation.SessionDetailsDao;
import com.revature.p2_lfg.service.game.classes.GameService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;

public class GameHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");


    private GameService gameService;

    public GameHandler(){
        this.gameService = new GameService(new GamesDao(), new SessionDetailsDao());
    }

    public Handler getGameSessionsList = ctx -> {
        dLog.debug("Getting game sessions select list");
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            ctx.json(gameService.getCurrentGameSessionList());
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };

    public Handler getGameGroupSessions = ctx -> {
        dLog.debug("Getting game group sessions by gameName: " + ctx.queryParam("gameId"));
        try{
            JWTInfo parsedJWT = JWTUtility.verifyUser(ctx.header("Authorization"));
            if(parsedJWT == null) {
                dLog.debug("User not validated with JWT Token");
                ctx.status(403);
                return;
            }
            try {
                int gameId = Integer.parseInt(ctx.queryParam("gameId"));
                ctx.json(gameService.getSelectedGameGroups(gameId));
            }catch(NullPointerException e){
                dLog.error(e.getMessage(), e);
            }
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
        }
    };
}
