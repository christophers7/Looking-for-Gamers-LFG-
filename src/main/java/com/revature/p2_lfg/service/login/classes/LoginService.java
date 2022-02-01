package com.revature.p2_lfg.service.login.classes;

import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.service.login.interfaces.LoginServiceable;
import com.revature.p2_lfg.utility.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("loginService")
public class LoginService implements LoginServiceable {

    @Autowired
    private LoginRepository loginRepository;

    public UserCredential getUserCredentialFromLogin(LoginRequest loginRequest) {
        try{
            return loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        }catch(Exception e){
            throw new InvalidInputException("User was not found");
        }
    }

    private UserCredential failResponse() {
        return new UserCredential(0, "", "");
    }

    @Override
    public UserCredential newAccount(NewUserCredentialsRequest newUserAccountRequest) throws InvalidInputException {
        return loginRepository.save(new UserCredential(
                0,
                newUserAccountRequest.getUsername(),
                newUserAccountRequest.getPassword()
        ));
    }

    @Override
    public boolean updateUserCredentialUsername(UpdateUsernameRequest updateUserCredentialRequest, JWTInfo parsedJWT) {
        UserCredential user = getUserWithUserID(parsedJWT.getUserId());
        return updateProfile(
                user,
                updateUserCredentialRequest.getUsername(),
                user.getPassword()) != null;
    }

    public UserCredential getUserWithUserID(int userId) {
        return loginRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
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
        credentials.setUsername(username);
        credentials.setPassword(password);
        return loginRepository.save(credentials);
    }

    private UserCredential getUserWithUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    @Override
    public boolean updateUserCredentialPassword(UpdatePasswordRequest updateUserCredentialRequest, JWTInfo parsedJWT) {
        UserCredential user = getUserWithUserID(parsedJWT.getUserId());
        return updateProfile(
                user,
                user.getUsername(),
                updateUserCredentialRequest.getPassword()) != null;
    }

}
