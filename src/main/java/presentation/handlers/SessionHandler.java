package presentation.handlers;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionHandler {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    public Handler createGroupSession = ctx -> {
        dLog.debug("Creating a group session: " + ctx.body());
        
    };
}
