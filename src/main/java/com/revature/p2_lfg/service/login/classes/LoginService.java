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
        try{
            return loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        }catch(Exception e){
            dLog.error(e.getMessage(), e);
            return failResponse();
        }
    }

    private UserCredential failResponse() {
        return new UserCredential(0, "", "");
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
        UserCredential user = getUserWithUserID(parsedJWT.getUserId());
        return updateProfile(
                user,
                updateUserCredentialRequest.getUsername(),
                user.getPassword()) != null;
    }

    public UserCredential getUserWithUserID(int userId) {
        dLog.debug("Getting stored UserCredentials with userCredential ID: " + userId);
        return loginRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
        dLog.debug("Attempting to reset UserPassword: " + resetPasswordRequest);
        return updateProfile(
                getUserWithUsername(resetPasswordRequest.getUsername()),
                resetPasswordRequest.getUsername(),
                getGeneratedString()) != null;
    }

    private String getGeneratedString() {
        Random random = new Random();
        random.nextInt(100000);
        return String.valueOf(random);
    }

    private UserCredential updateProfile(UserCredential credentials, String username, String password){
        dLog.debug("Updating Profile: " + credentials + " - Username: " + username + " - Password: " + password);
        LoginValidation.validateUsername(username);
        LoginValidation.validatePassword(password);
        credentials.setUsername(username);
        credentials.setPassword(password);
        return loginRepository.save(credentials);
    }

    private UserCredential getUserWithUsername(String username) {
        dLog.debug("Getting User Credentials with username");
        return loginRepository.findByUsername(username);
    }

    @Override
    public boolean updateUserCredentialPassword(UpdatePasswordRequest updateUserCredentialRequest, JWTInfo parsedJWT) {
        dLog.debug("Attempting to update password: " + updateUserCredentialRequest);
        UserCredential user = getUserWithUserID(parsedJWT.getUserId());
        return updateProfile(
                user,
                user.getUsername(),
                updateUserCredentialRequest.getPassword()) != null;
    }

}
