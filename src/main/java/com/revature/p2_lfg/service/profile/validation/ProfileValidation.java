package com.revature.p2_lfg.service.profile.validation;

import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.utility.JWTUtility;

import java.util.regex.Pattern;

public class ProfileValidation {

    public static void validateUpdateProfile(UpdateUserProfileRequest updateUserProfileRequest) throws InvalidInputException {
        try{
            validateFirstName(updateUserProfileRequest.getFirstName());
            validateLastName(updateUserProfileRequest.getLastName());
            validateEmail(updateUserProfileRequest.getEmail());
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidInputException("NULL VALUE RECEIVED");
        }
    }

    public static void validateFirstName(String firstName) throws InvalidInputException {
        if(firstName.contentEquals("")) throw new InvalidInputException("Empty new firstName input");
        if(firstName.trim().length() < 2) throw new InvalidInputException("Too Short new firstName input");
        if(firstName.trim().length() > 25) throw new InvalidInputException("Too Long new firstName input");

    }

    public static void validateLastName(String lastName) throws InvalidInputException {
        if(lastName.trim().contentEquals("")) throw new InvalidInputException("Empty new lastName input");
        if(lastName.trim().length() < 2) throw new InvalidInputException("Too Short new lastName input");
        if(lastName.trim().length() > 25) throw new InvalidInputException("Too Long new lastName input");
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if(!Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(email)
                .matches()) throw new InvalidInputException("Invalid Email format");
    }


    public static void validateUsername(String username) {
        if(username.contentEquals("")) throw new InvalidInputException("Empty new username input");
        if(username.trim().length() < 2) throw new InvalidInputException("Too Short new username input");
        if(username.trim().length() > 25) throw new InvalidInputException("Too Long new username input");
    }

    public static void validateProfileResponse(ProfileResponse response) {
        try{
            validateUsername(response.getUsername());
            validateEmail(response.getEmail());
            JWTUtility.verifyUser(response.getJWT());
        }catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidInputException("NULL VALUE RECEIVED");
        }
    }
}
