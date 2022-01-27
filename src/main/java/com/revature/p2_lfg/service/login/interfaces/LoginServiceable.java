package com.revature.p2_lfg.service.login.interfaces;

import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.utility.JWTInfo;

public interface LoginServiceable {

    /**
     * Method used to retrieve a UserCredential entity associated with a table entity in the database.
     * Uses the LoginRequest model, a request from the FrontEnd to the server to authenticate the login.
     * @param loginRequest A model associated with a request from the FrontEnd
     * @return UserCredential An entity associated with the UserCredential database Table.
     * Null is returned if an exception is thrown or method fails to process.
     */
    UserCredential getUserCredentialFromLogin(LoginRequest loginRequest);

    /**
     * Method used to create a new UserCredential row through the conversion of a
     * NewUserCredentialRequest model from the front end as a POST request and putting it into the database
     * after validation and cleaning of the input.
     * If the input is invalid then the exceptions are caught and a null value is thrown and must be checked
     * before proceeding with this method.
     * @param newUserCredentialsRequest is the front end model sent and converted into the object for processing
     * @return Integer that is linked to the UserID in the database table.
     * -1 is returned if no profile is found associated with the model.
     */
    Integer newUserCredential(NewUserCredentialsRequest newUserCredentialsRequest);

    /**
     * This method is used to create a new UserCredential Account. It is the first part in the process of
     * registering a new user, as the UserCredential entity is the cornerstone of the database and links
     * to many other aspects of the users profile.
     * @param newUserAccountRequest The model object mapping the front end request for a new User Account
     * @return UserCredential that is associated with the entity mapping the table in the database.
     * Null is returned if an exception is thrown or method fails to process.
     */
    UserCredential newAccount(NewUserCredentialsRequest newUserAccountRequest);


    boolean updateUserCredentialUsername(UpdateUsernameRequest updateUserCredentialRequest, JWTInfo parsedJWT);

    boolean resetPassword(ResetPasswordRequest bodyAsClass);

    UserCredential getUserWithUserID(int userId);

    boolean updateUserCredentialPassword(UpdatePasswordRequest updateUserCredentialRequest, JWTInfo parsedJWT);

}
