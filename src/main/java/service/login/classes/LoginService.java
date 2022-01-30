package service.login.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.login.*;
import repository.DAO.implementation.UserCredentialsDao;
import repository.entities.UserCredential;
import service.login.exceptions.InvalidInputException;
import service.login.interfaces.LoginServiceable;
import service.login.validation.LoginValidation;
import utility.JWTInfo;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class LoginService implements LoginServiceable {
    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private final UserCredentialsDao userCredDao;

    public LoginService(UserCredentialsDao userCredDao) {
        this.userCredDao = userCredDao;
    }

    public UserCredential getUserCredentialFromLogin(LoginRequest loginRequest) {
        dLog.debug("Validating user login attempt: " + loginRequest);
        return userCredDao.getUserWithUsername(
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

    @Override
    public boolean updateUserCredentialUsername(UpdateUsernameRequest updateUserCredentialRequest, JWTInfo parsedJWT) {
        dLog.debug("Attempting to update User name: " + updateUserCredentialRequest);
        try{
            LoginValidation.validateUsername(updateUserCredentialRequest.getUsername());
            UserCredential storedUser = getUserWithUserID(parsedJWT.getUserId());
            storedUser.setUserLogin(updateUserCredentialRequest.getUsername());
            userCredDao.updateUser(storedUser);
            iLog.info("Updated username, userID: " + parsedJWT.getUserId());
            return true;
        } catch (InvalidInputException e) {
            dLog.error(e.getMessage(), e);
            return false;
        }
    }

    public UserCredential getUserWithUserID(int userId) {
        dLog.debug("Getting stored UserCredentials with userCredential ID: " + userId);
        return userCredDao.getUser(new UserCredential(userId, "", ""));
    }

    @Override
    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
        dLog.debug("Attempting to reset UserPassword: " + resetPasswordRequest);
        try{
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);
            UserCredential storedUserCredential = getUserCredentialFromLogin(new LoginRequest(resetPasswordRequest.getUsername(), ""));
            storedUserCredential.setUserPass(generatedString);
            userCredDao.updateUser(storedUserCredential);
            iLog.info("Reset password for user: " + resetPasswordRequest);
            return true;
        }catch (Exception e){
            dLog.error(e.getMessage(), e);
            return false;
        }

    }

    @Override
    public boolean updateUserCredentialPassword(UpdatePasswordRequest updateUserCredentialRequest, JWTInfo parsedJWT) {
        dLog.debug("Attempting to update password: " + updateUserCredentialRequest);
        try{
            LoginValidation.validatePassword(updateUserCredentialRequest.getPassword());
            UserCredential storedUser = getUserWithUserID(parsedJWT.getUserId());
            storedUser.setUserPass(updateUserCredentialRequest.getPassword());
            userCredDao.updateUser(storedUser);
            iLog.info("Updated password, userID: " + parsedJWT.getUserId());
            return true;
        } catch (InvalidInputException e) {
            dLog.error(e.getMessage(), e);
            return false;
        }
    }
}
