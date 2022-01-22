package service.profile.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.models.ProfileResponse;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.UserCredential;
import service.profile.interfaces.ProfileServiceable;

public class ProfileService implements ProfileServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private UserProfileDao userProfDao;

    public ProfileService() {
        this.userProfDao = new UserProfileDao();
    }

    @Override
    public ProfileResponse getProfileResponse(UserCredential userCredential) {
//        dLog.debug("Getting Profile Response with User Credentials: " + userCredential);
        return null;
    }
}
