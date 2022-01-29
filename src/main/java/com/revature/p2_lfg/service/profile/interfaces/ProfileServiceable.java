package com.revature.p2_lfg.service.profile.interfaces;

import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.entities.user.UserProfile;

public interface ProfileServiceable {

    /**
     * This is used to get convert the login input viewed as a UserCredential Object into a Profile Response for the front end models
     * The only necessary field in userCredentials is the ID of the user that it is attached to.
     * @param userCredential The User ID, Username, Password entity from the DAO layer, only ID is necessary
     * @return ProfileResponse A model object mapping the JSON response for the front end.
     * Null is returned if an exception is thrown or method fails to process.
     */
    ProfileResponse getProfileResponse(UserCredential userCredential);

    /**
     * This is used to convert an entity object of UserProfile to a ProfileResponse.
     * All required parameters cannot be null, but they can be empty.
     * @param userProfile UserProfile entity from the DAO layer
     * @return ProfileResponse A model object mapping the JSON response for the front end.
     * Null is returned if an exception is thrown or method fails to process.
     */
    ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile);

    /**
     * Method used to get the UserProfile entity from the DAO layer.
     * The only required variable is the userId, the rest is populated by the method.
     * @param userId The unique ID associated with the UserCredential Entity
     * @return UserProfile An entity associated with the UserProfile in the database.
     * Null is returned if an exception is thrown or method fails to process.
     */
    UserProfile getUserProfile(int userId);

    /**
     * Method used to create a new UserProfile, a valid and previously created UserCredential entity must be made
     * prior. In the process of making the UserProfile, a valid email is also required.
     * This will populate a row associated with this entity in the database through the DAO layer.
     * @param newUserCredential Newly created UserCredential associated with the UserCredential table in the database
     * @param email valid String email associated with the User created recently
     * @return UserProfile An entity associated with the newly created UserProfile in the database.
     * Null is returned if an exception is thrown or method fails to process.
     */
    ProfileResponse newUserProfile(UserCredential newUserCredential, String email);

    /**
     * This method is used to update the users profile from the handler level. It is clear in its use for the handler,
     * and is made up of other methods within the class. Do not use this or edit this for anything other than updating
     * the profile and getting a response back.
     * @param updateUserProfileRequest The model mapping the front end request to update a users profile
     * @param storedUserProfile The stored user profile entity from the database to be updated
     * @return ProfileResponse as a direct return to a JSON object
     */
    ProfileResponse updateProfileWithRequest(UpdateUserProfileRequest updateUserProfileRequest, UserProfile storedUserProfile);
}
