package com.revature.p2_lfg.service.profile.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.DAO.implementation.UserProfileDao;
import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.repository.entities.UserProfile;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.service.profile.interfaces.ProfileServiceable;
import com.revature.p2_lfg.service.profile.validation.ProfileValidation;
import com.revature.p2_lfg.utility.JWTUtility;

public class ProfileService implements ProfileServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private final UserProfileDao userProfDao;

    public ProfileService(UserProfileDao userProfDao) {
        this.userProfDao = userProfDao;
    }

    @Override
    public ProfileResponse getProfileResponse(UserCredential userCredential) {
        dLog.debug("Getting Profile Response with User Credentials: " + userCredential);
        return convertUserProfileToProfileResponse(userProfDao.getUserProfileWithUserCredentials(userCredential));
    }

    public UserProfile getUserProfile(int columnId) {
        dLog.debug("Getting User Profile: " + columnId);
        return userProfDao.getUserProfile(
                new UserProfile(
                        columnId,
                        new UserCredential(),
                        "",
                        "",
                        ""
                )
        );
    }

    @Override
    public ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile) {
        dLog.debug("Converting User profile into profile Response: " + userProfile);
        String JWT = JWTUtility.generateJWT(userProfile);
        return new ProfileResponse(
                userProfile.getUserCredential().getUserLogin(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getEmail(),
                JWT
        );
    }

    @Override
    public UserProfile newUserProfile(UserCredential newUserCredential, String email) {
        dLog.debug("Creating new UserProfile: " + newUserCredential + " " + email);
        return getUserProfile(userProfDao.createProfile(new UserProfile(0,newUserCredential,"","",email)));
    }

    @Override
    public ProfileResponse updateProfileWithRequest(UpdateUserProfileRequest updateUserProfileRequest, UserProfile storedUserProfile) {
        dLog.debug("Updating user profile with Request: " + updateUserProfileRequest + "\nStored Profile: " + storedUserProfile);
        try {
            ProfileValidation.validateUpdateProfile(updateUserProfileRequest);
            storedUserProfile.setFirstName(updateUserProfileRequest.getFirstName());
            storedUserProfile.setLastName(updateUserProfileRequest.getLastName());
            storedUserProfile.setEmail(updateUserProfileRequest.getEmail());
            userProfDao.updateUserProfile(storedUserProfile);
            return convertUserProfileToProfileResponse(userProfDao.getUserProfile(storedUserProfile));
        } catch (InvalidInputException e) {
            dLog.debug("Invalid Input for Updating User Profile: " + updateUserProfileRequest);
            dLog.error(e.getMessage(), e);
            return null;
        }
    }
}
