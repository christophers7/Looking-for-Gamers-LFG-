package service.profile.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.DAO.implementation.UserProfileDao;
import repository.entities.PublicDetails;
import repository.entities.UserProfile;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfileServiceTest {

    @Mock
    private UserProfileDao mockUserProfileDao;

    private ProfileService profileService;

    //Variables
    private PublicDetails userProfileRequest;

    @BeforeAll
    void setUp(){
//        MockitoAnnotations.initMocks(this);
//
//        userProfileRequest = new UserProfile(
//            0, "", "",
//        );
//
//
//        Mockito.when(mockUserProfileDao.getUserProfile(userProfileRequest))
    }

    @Test
    void getProfileResponse() {
    }
}