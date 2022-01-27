package com.revature.p2_lfg.service.login.validation;

import com.revature.p2_lfg.presentation.models.login.NewUserCredentialsRequest;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;

import java.util.regex.Pattern;

public class LoginValidation {

    public static void validateNewUserCredentials(NewUserCredentialsRequest newUserCredentialsRequest) throws InvalidInputException {
        try{
            validateUsername(newUserCredentialsRequest.getUsername());
            validatePassword(newUserCredentialsRequest.getPassword());
            validateEmail(newUserCredentialsRequest.getEmail());
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidInputException("NULL VALUE RECEIVED");
        }
    }

    public static void validateUsername(String username) throws InvalidInputException {
        if(username.contentEquals("")) throw new InvalidInputException("Empty new username input");
        if(username.trim().length() < 4) throw new InvalidInputException("Too Short new username input");
        if(username.trim().length() > 25) throw new InvalidInputException("Too Long new username input");
    }

    public static void validatePassword(String password) throws InvalidInputException {
        if(password.trim().contentEquals("")) throw new InvalidInputException("Empty new Password input");
        if(password.length() < 4) throw new InvalidInputException("Too Short new Password input");
        if(password.trim().length() > 25) throw new InvalidInputException("Too Long new Password input");
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if(!Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(email)
                .matches()) throw new InvalidInputException("Invalid Email format");
    }
}
