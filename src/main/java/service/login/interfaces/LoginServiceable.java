package service.login.interfaces;

import presentation.models.LoginRequest;
import repository.entities.UserCredential;

public interface LoginServiceable {

    UserCredential getUserCredential(LoginRequest loginRequest);
}
