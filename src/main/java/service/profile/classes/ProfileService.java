package service.profile.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.ProfileResponse;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.UserCredential;
import service.profile.interfaces.ProfileServiceable;
import utility.JWTUtility;

public class ProfileService implements ProfileServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private UserProfileDao userProfDao;

    public ProfileService() {
        this.userProfDao = new UserProfileDao();
    }

    @Override
    public ProfileResponse getProfileResponse(UserCredential userCredential) {
        dLog.debug("Getting Profile Response with User Credentials: " + userCredential);
        return convertUserCredentialToProfileResponse(userCredential);
    }

    public ProfileResponse convertUserCredentialToProfileResponse(UserCredential userCredential) {
        String JWT = JWTUtility.generateJWT(userCredential);
        return new ProfileResponse(
                userCredential.getUserLogin(),
                userCredential.getUserProfile().getFirstName(),
                userCredential.getUserProfile().getLastName(),
                userCredential.getUserProfile().getEmail(),
                userCredential.getPublicDetails().getGamerTag(),
                JWT
        );
    }
}
