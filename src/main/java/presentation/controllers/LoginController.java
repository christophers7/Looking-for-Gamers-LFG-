package presentation.controllers;

import io.javalin.Javalin;
import presentation.handlers.LoginHandler;

import static io.javalin.apibuilder.ApiBuilder.*;

public class LoginController {

    private final LoginHandler loginHandler = new LoginHandler();

    public void setEndpoints(Javalin app) {

        app.routes(() -> {

            path("/login", () -> {

                path("/check", () -> {
                    post(loginHandler.checkLogin);
                });

            });


        });
    }
}
