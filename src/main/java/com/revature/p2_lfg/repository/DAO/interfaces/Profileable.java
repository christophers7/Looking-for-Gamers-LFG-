package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.repository.entities.UserProfile;

public interface Profileable {

    int createProfile(UserProfile profile);

    UserProfile getUserProfile(UserProfile profile);

    void updateUserProfile(UserProfile profile);

    void deleteUserProfile(UserProfile profile);

    UserProfile getUserProfileWithUserCredentials(UserCredential userCredential);
}
