package repository.DAO.interfaces;

import repository.entities.UserCredential;

public interface Loginable {

    int createUser(UserCredential userCredential);

    UserCredential getUser(UserCredential userCredential);

    void updateUser(UserCredential userCredential);

    void deleteUser(UserCredential userCredential);

    UserCredential getUserWithUsername(UserCredential userCredential);
}
