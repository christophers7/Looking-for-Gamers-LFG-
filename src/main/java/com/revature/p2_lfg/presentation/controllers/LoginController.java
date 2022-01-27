package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.handlers.LoginHandler;
import io.javalin.Javalin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static io.javalin.apibuilder.ApiBuilder.*;

@Controller("loginController")
public class LoginController {

    @Autowired
    private LoginHandler loginHandler;

    public void setEndpoints(Javalin app) {

        app.routes(() -> {

            path("/login", () -> {

                path("/check", () -> {
                    post(loginHandler.checkLogin);
                });

                path("/new", () -> {
                   post(loginHandler.newLogin);
                });

                path("/update-username", () -> {
                   patch(loginHandler.updateUsername);
                });

                path("/update-password", () -> {
                   patch(loginHandler.updatePassword);
                });

                path("/reset", () -> {
                    patch(loginHandler.resetPassword);
                });

            });


        });
    }
}
