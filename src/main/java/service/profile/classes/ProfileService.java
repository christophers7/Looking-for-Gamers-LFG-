package service.profile.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.ProfileResponse;
import presentation.models.UpdateUserProfileRequest;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.UserCredential;
import repository.entities.UserProfile;
import service.login.exceptions.InvalidInputException;
import service.profile.interfaces.ProfileServiceable;
import service.profile.validation.ProfileValidation;
import utility.JWTUtility;

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
