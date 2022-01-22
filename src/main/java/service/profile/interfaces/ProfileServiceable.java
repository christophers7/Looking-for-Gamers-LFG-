package service.profile.interfaces;

import presentation.models.ProfileResponse;
import repository.entities.UserCredential;

public interface ProfileServiceable {

    ProfileResponse getProfileResponse(UserCredential userCredential);

    ProfileResponse convertUserCredentialToProfileResponse(UserCredential userCredential);
}
