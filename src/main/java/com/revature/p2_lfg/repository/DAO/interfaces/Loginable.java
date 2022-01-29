package com.revature.p2_lfg.repository.DAO.interfaces;

import com.revature.p2_lfg.repository.entities.UserCredential;

public interface Loginable {

    int createUser(UserCredential userCredential);

    UserCredential getUser(UserCredential userCredential);

    void updateUser(UserCredential userCredential);

    void deleteUser(UserCredential userCredential);

//    UserCredential getUserWithUsername(UserCredential userCredential);

    UserCredential getUserWithUsername(String waitingUsername);
}
