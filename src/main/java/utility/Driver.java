package utility;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.hibernate.Session;
import repository.DAO.util.HibernateSessionFactory;

import static io.javalin.Javalin.create;

public class Driver {
    public static void main(String[] args){
        Javalin app = create(JavalinConfig::enableCorsForAllOrigins).start(6432);
        ServerConfig.configureServerProperties(app);
        ServerConfig.setEndpoints(app);
        Session session = HibernateSessionFactory.getSession();

    }
}
