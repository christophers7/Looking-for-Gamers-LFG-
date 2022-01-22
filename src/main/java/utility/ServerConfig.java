package utility;

import io.javalin.Javalin;
import presentation.controllers.LoginController;
import presentation.controllers.UserController;

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
        loginController.setEndpoints(app);
        userController.setEndpoints(app);
    }
}
