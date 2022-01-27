package com.revature.p2_lfg.utility;

import com.revature.p2_lfg.P2SpringLfgApplication;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.hibernate.Session;
import com.revature.p2_lfg.repository.DAO.util.HibernateSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static io.javalin.Javalin.create;

@SpringBootApplication
public class Driver {
    public static void main(String[] args){
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ConfigurableApplicationContext context = SpringApplication.run(Driver.class, args);

//        Javalin app = create(JavalinConfig::enableCorsForAllOrigins).start(6432);
//        ServerConfig.configureServerProperties(app);
//        ServerConfig.setEndpoints(app);
//        Session session = HibernateSessionFactory.getSession();
    }
}
