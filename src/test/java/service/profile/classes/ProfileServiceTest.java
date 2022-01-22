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
    private UserProfileDao UserProfileDao;
    private ProfileService profileService;

    //Variables
    private UserCredential storedUserCredential;

    private UserProfile storedUserProfile;
    private PublicDetails storedPublicDetails;

    private ProfileResponse profileResponse;


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

        storedUserProfile = new UserProfile(
                1, firstName, lastName, email
        );

        storedPublicDetails = new PublicDetails(
                1, storedUserCredential, new Games(), gamerTag
        );

        storedUserCredential = new UserCredential(
                1, username, password, storedUserProfile, storedPublicDetails
        );

        profileResponse = new ProfileResponse(
                storedUserCredential.getUserLogin(),
                storedUserCredential.getUserProfile().getFirstName(),
                storedUserCredential.getUserProfile().getLastName(),
                storedUserCredential.getUserProfile().getEmail(),
                storedUserCredential.getPublicDetails().getGamerTag(),
                JWT
        );

        profileService = new ProfileService();
    }

    @Test
    void getProfileResponse() {
        assertEquals(profileResponse, profileService.getProfileResponse(storedUserCredential));
    }
}