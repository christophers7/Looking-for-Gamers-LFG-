package presentation.controllers;

import io.javalin.Javalin;
import presentation.handlers.UserHandler;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserController {

    private final UserHandler userHandler = new UserHandler();

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