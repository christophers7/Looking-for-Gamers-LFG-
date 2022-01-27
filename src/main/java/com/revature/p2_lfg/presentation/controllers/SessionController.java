package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.handlers.GameHandler;
import com.revature.p2_lfg.presentation.handlers.SessionHandler;
import io.javalin.Javalin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static io.javalin.apibuilder.ApiBuilder.*;

@Controller("sessionController")
public class SessionController {

    @Autowired
    private SessionHandler sessionHandler;
    @Autowired
    private GameHandler gameHandler;

    public void setEndpoints(Javalin app) {
        app.routes(() -> {

            path("/game", () -> {

              path("/available", () ->{
                  get(gameHandler.getGameSessionsList);
              });

              path("/select", () -> {
                 get(gameHandler.getGameGroupSessions);
              });

            });

            path("/group", () -> {

                path("/host", () -> {
                    post(sessionHandler.createGroupSession);
                });
                

            });

        });
    }
}
