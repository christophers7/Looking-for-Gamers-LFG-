package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.handlers.UserHandler;
import io.javalin.Javalin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static io.javalin.apibuilder.ApiBuilder.*;

@Controller("userController")
public class UserController {

    @Autowired
    private UserHandler userHandler;

    public void setEndpoints(Javalin app) {

        app.routes(() -> {

            path("/user", () -> {

                path("new-profile", () -> {
                    patch(userHandler.updateProfile);
                });

                path("profile", () -> {
                   get(userHandler.getUserProfile);
                });

            });

        });
    }
}
