package repository.DAO.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DAO.interfaces.Profileable;
import repository.entities.UserProfile;

public class UserProfileDao implements Profileable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public int createProfile(UserProfile profile) {
        return 0;
    }

    @Override
    public UserProfile getUserProfile(UserProfile profile) {
        return null;
    }

    @Override
    public void updateUserProfile(UserProfile profile) {

    }

    @Override
    public void deleteUserProfile(UserProfile profile) {

    }
}
