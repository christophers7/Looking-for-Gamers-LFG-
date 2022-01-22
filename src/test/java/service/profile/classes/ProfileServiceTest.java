package service.profile.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import presentation.models.ProfileResponse;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.Games;
import repository.entities.PublicDetails;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfileServiceTest {

    @Mock
    private UserProfileDao userProfileDao;

    private ProfileService profileService;

    //Variables
    private UserCredential storedUserCredential;

    private UserProfile storedUserProfile;
    private PublicDetails storedPublicDetails;

    private ProfileResponse profileResponse;

    private UserProfile storedNewProfile;
    private UserCredential storedNewUserCredentials;
    private String newEmail;

    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);

        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email@email.com";
        String gamerTag = "gamerTag";
        String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJmaXJzdE5hbWUiLCJsYXN0TmFtZSI6Imxhc3ROYW1lIiwidXNlcm5hbWUiOiJ1c2VybmFtZSIsInVzZXJJZCI6MSwiYWNjb3VudCI6dHJ1ZX0.OBsS9C--r2fqUGdhoy_yK8ER4WzSX5eUdKJ5is_p7_c";

        int newUserProfileColumnId = 2;
        newEmail = "newEmail@email.com";

        storedUserCredential = new UserCredential(
                1, username, password
        );

        storedUserProfile = new UserProfile(
                1, storedUserCredential, firstName, lastName, email
        );

        storedPublicDetails = new PublicDetails(
                1, storedUserCredential, new Games(), gamerTag
        );

        profileResponse = new ProfileResponse(
                storedUserProfile.getUserID().getUserLogin(),
                storedUserProfile.getFirstName(),
                storedUserProfile.getLastName(),
                storedUserProfile.getEmail(),
                JWT
        );

        storedNewUserCredentials = new UserCredential(
                2, "newUsername", "newPassword"
        );

        UserProfile userProfileRequest = new UserProfile(
                0, new UserCredential(storedUserCredential.getUserID(), "", ""), "", "", ""
        );

        UserProfile userProfileNewRequest = new UserProfile(
            0, new UserCredential(storedNewUserCredentials.getUserID(), "", ""), "", "", "");

        storedNewProfile = new UserProfile(
                newUserProfileColumnId, storedNewUserCredentials, "", "", newEmail
        );

        Mockito.when(userProfileDao.getUserProfile(userProfileRequest)).thenReturn(storedUserProfile);
        Mockito.when(userProfileDao.createProfile(new UserProfile(0,storedNewUserCredentials, "", "", newEmail))).thenReturn(newUserProfileColumnId);
        Mockito.when(userProfileDao.getUserProfile(userProfileNewRequest)).thenReturn(storedNewProfile);
        profileService = new ProfileService(userProfileDao);
    }

    @Test
    void getProfileResponseTest() {
        assertEquals(profileResponse, profileService.getProfileResponse(storedUserCredential));
    }

    @Test
    void newUserProfileTest(){
        assertEquals(storedNewProfile, profileService.newUserProfile(storedNewUserCredentials, newEmail));
    }

}