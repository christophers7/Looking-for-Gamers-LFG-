package service.profile.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.ProfileResponse;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.UserCredential;
import repository.entities.UserProfile;
import service.profile.interfaces.ProfileServiceable;
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
        return convertUserProfileToProfileResponse(getUserProfile(userCredential.getUserID()));
    }

    public UserProfile getUserProfile(int userId) {
        dLog.debug("Getting User Profile: " + userId);
        return userProfDao.getUserProfile(
                new UserProfile(
                        0,
                        new UserCredential(userId, "", ""),
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
                userProfile.getUserID().getUserLogin(),
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
}
