package service.login.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.LoginRequest;
import repository.DAO.implementation.UserCredentialsDao;
import repository.entities.PublicDetails;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

public class LoginService {
    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private final UserCredentialsDao userCredDao;

    public LoginService(UserCredentialsDao userCredDao) {
        this.userCredDao = userCredDao;
    }

    public UserCredential getUserCredential(LoginRequest loginRequest) {
        dLog.debug("Validating user login attempt: " + loginRequest);
        return userCredDao.getUser(
                new UserCredential(
                        0,
                        loginRequest.getUsername(),
                        loginRequest.getPassword(),
                        new UserProfile(),
                        new PublicDetails()));
    }
}
