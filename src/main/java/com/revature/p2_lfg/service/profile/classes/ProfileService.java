package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.repository.interfaces.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.entities.user.UserProfile;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.service.profile.interfaces.ProfileServiceable;
import com.revature.p2_lfg.service.profile.validation.ProfileValidation;
import com.revature.p2_lfg.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("profileService")
public class ProfileService implements ProfileServiceable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public ProfileResponse getProfileResponse(UserCredential userCredential) {
        dLog.debug("Getting Profile Response with User Credentials: " + userCredential);
        return convertUserProfileToProfileResponse(userProfileRepository.findByUserId(userCredential.getUserId()).orElse(null));
    }

    public UserProfile getUserProfile(int columnId) {
        dLog.debug("Getting User Profile: " + columnId);
        return userProfileRepository.findById(columnId).orElse(null);
    }

    @Override
    public ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile) {
        dLog.debug("Converting User profile into profile Response: " + userProfile);
        String JWT = JWTUtility.generateJWT(userProfile);
        return new ProfileResponse(
                userProfile.getUserCredential().getUsername(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getEmail(),
                JWT
        );
    }

    @Override
    public ProfileResponse newUserProfile(UserCredential newUserCredential, String email) {
        dLog.debug("Creating new UserProfile: " + newUserCredential + " " + email);
        return convertUserProfileToProfileResponse(userProfileRepository.save(new UserProfile(0,newUserCredential,"","",email)));
    }

    @Override
    public ProfileResponse updateProfileWithRequest(UpdateUserProfileRequest updateUserProfileRequest, UserProfile storedUserProfile) {
        dLog.debug("Updating user profile with Request: " + updateUserProfileRequest + "\nStored Profile: " + storedUserProfile);
        try {
            ProfileValidation.validateUpdateProfile(updateUserProfileRequest);
            storedUserProfile.setFirstName(updateUserProfileRequest.getFirstName());
            storedUserProfile.setLastName(updateUserProfileRequest.getLastName());
            storedUserProfile.setEmail(updateUserProfileRequest.getEmail());
            return convertUserProfileToProfileResponse(userProfileRepository.save(storedUserProfile));
        } catch (InvalidInputException e) {
            dLog.debug("Invalid Input for Updating User Profile: " + updateUserProfileRequest);
            dLog.error(e.getMessage(), e);
            return null;
        }
    }
}
