package repository.DAO.interfaces;

import repository.entities.UserCredential;
import repository.entities.UserProfile;

public interface Profileable {

    int createProfile(UserProfile profile);

    UserProfile getUserProfile(UserProfile profile);

    void updateUserProfile(UserProfile profile);

    void deleteUserProfile(UserProfile profile);

    UserProfile getUserProfileWithUserCredentials(UserCredential userCredential);
}
