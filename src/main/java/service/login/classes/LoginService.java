package service.login.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.LoginRequest;
import presentation.models.NewUserCredentialsRequest;
import repository.DAO.implementation.UserCredentialsDao;
import repository.entities.UserCredential;
import service.login.exceptions.InvalidInputException;
import service.login.interfaces.LoginServiceable;
import service.validation.LoginValidation;

public class LoginService implements LoginServiceable {
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
                        loginRequest.getPassword()));
    }

    @Override
    public UserCredential newAccount(NewUserCredentialsRequest newUserAccountRequest) {
        dLog.debug("Creating new Account, generating new User Credential: " + newUserAccountRequest);
        return userCredDao.getUser(
                new UserCredential(
                        newUserCredential(newUserAccountRequest),
                        "",
                        ""));
    }

    @Override
    public Integer newUserCredential(NewUserCredentialsRequest newUserCredentialsRequest) {
        dLog.debug("Creating new userCredentials through database: " + newUserCredentialsRequest);
        try {
            LoginValidation.validateNewUserCredentials(newUserCredentialsRequest);
            return userCredDao.createUser(new UserCredential(
                    0,
                    newUserCredentialsRequest.getUsername(),
                    newUserCredentialsRequest.getPassword()
            ));
        } catch (InvalidInputException e) {
            dLog.debug("Failed validation of new User Credentials");
            dLog.error(e.getMessage(), e);
            return -1;
        } catch (NullPointerException e){
            dLog.debug("Failed validation of new User Credentials due to NULL VALUE");
            dLog.error(e.getMessage(), e);
            return -1;
        }

    }


}
