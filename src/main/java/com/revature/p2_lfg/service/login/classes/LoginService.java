package com.revature.p2_lfg.service.login.classes;

import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.service.login.interfaces.LoginServiceable;
import com.revature.p2_lfg.service.login.validation.LoginValidation;
import com.revature.p2_lfg.utility.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service("loginService")
public class LoginService implements LoginServiceable {
    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private LoginRepository loginRepository;

    public UserCredential getUserCredentialFromLogin(LoginRequest loginRequest) {
        dLog.debug("Validating user login attempt: " + loginRequest);
        return loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Override
    public UserCredential newAccount(NewUserCredentialsRequest newUserAccountRequest) throws InvalidInputException {
        dLog.debug("Creating new Account, generating new User Credential: " + newUserAccountRequest);
        LoginValidation.validateNewUserCredentials(newUserAccountRequest);
        return loginRepository.save(new UserCredential(
                0,
                newUserAccountRequest.getUsername(),
                newUserAccountRequest.getPassword()
        ));
    }

    @Override
    public boolean updateUserCredentialUsername(UpdateUsernameRequest updateUserCredentialRequest, JWTInfo parsedJWT) {
        dLog.debug("Attempting to update User name: " + updateUserCredentialRequest);
        try{
            LoginValidation.validateUsername(updateUserCredentialRequest.getUsername());
            UserCredential storedUser = getUserWithUserID(parsedJWT.getUserId());
            storedUser.setUsername(updateUserCredentialRequest.getUsername());
            loginRepository.save(storedUser);
            iLog.info("Updated username, userID: " + parsedJWT.getUserId());
            return true;
        } catch (InvalidInputException e) {
            dLog.error(e.getMessage(), e);
            return false;
        }
    }

    public UserCredential getUserWithUserID(int userId) {
        dLog.debug("Getting stored UserCredentials with userCredential ID: " + userId);
        return loginRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
        dLog.debug("Attempting to reset UserPassword: " + resetPasswordRequest);
        try{
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);
            UserCredential storedUserCredential = getUserCredentialFromLogin(new LoginRequest(resetPasswordRequest.getUsername(), ""));
            storedUserCredential.setPassword(generatedString);
            loginRepository.save(storedUserCredential);
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
            storedUser.setPassword(updateUserCredentialRequest.getPassword());
            loginRepository.save(storedUser);
            iLog.info("Updated password, userID: " + parsedJWT.getUserId());
            return true;
        } catch (InvalidInputException e) {
            dLog.error(e.getMessage(), e);
            return false;
        }
    }

}
