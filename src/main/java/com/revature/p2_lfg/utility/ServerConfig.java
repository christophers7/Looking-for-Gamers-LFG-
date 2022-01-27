package com.revature.p2_lfg.utility;

import io.javalin.Javalin;
import com.revature.p2_lfg.presentation.controllers.LoginController;
import com.revature.p2_lfg.presentation.controllers.SessionController;
import com.revature.p2_lfg.presentation.controllers.UserController;

public class ServerConfig {

    public static void configureServerProperties(Javalin app){
        prefer405(app);
    }

    private static void prefer405(Javalin app) {
        app._conf.prefer405over404 = true;
    }

    public static void setEndpoints(Javalin app) {
        LoginController loginController = new LoginController();
        UserController userController = new UserController();
        SessionController sessionController = new SessionController();
        loginController.setEndpoints(app);
        userController.setEndpoints(app);
        sessionController.setEndpoints(app);
    }
}
