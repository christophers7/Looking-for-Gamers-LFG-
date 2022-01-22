package service.login.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import presentation.models.LoginRequest;
import repository.DAO.implementation.UserCredentialsDao;
import repository.entities.Games;
import repository.entities.PublicDetails;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceTest {
    @Mock
    private UserCredentialsDao userCredDao;

    private LoginService loginService;

    //Variables

    private LoginRequest loginRequest;
    private UserCredential userCredRequest;
    private UserCredential storedUserCredential;

    private UserProfile storedUserProfile;
    private PublicDetails storedPublicDetails;

   @BeforeAll
   void setUp() {
    MockitoAnnotations.openMocks(this);

    loginRequest = new LoginRequest(
            "username", "password"
    );

    userCredRequest = new UserCredential(
            0, loginRequest.getUsername(), loginRequest.getPassword(), new UserProfile(), new PublicDetails()
    );

    storedUserProfile = new UserProfile(
            1, "firstName", "lastName", "email"
    );

    storedPublicDetails = new PublicDetails(
            1, storedUserCredential, new Games(), "gamerTag"
    );

    storedUserCredential = new UserCredential(
            1, loginRequest.getUsername(), loginRequest.getPassword(), storedUserProfile, storedPublicDetails
    );

    Mockito.when(userCredDao.getUser(userCredRequest)).thenReturn(storedUserCredential);

    loginService = new LoginService(userCredDao);
   }

    @Test
    void getUserCredentialTest() {
       assertEquals(storedUserCredential, loginService.getUserCredential(loginRequest));
    }

    @Test
    void invalidUsernameTest() {
       assertNull(loginService.getUserCredential(new LoginRequest("wrong", "pass")));
    }

    @Test
    void invalidPasswordTest() {
        assertNull(loginService.getUserCredential(new LoginRequest("user", "wrong")));
    }

    @Test
    void emptyInputTest() {
        assertNull(loginService.getUserCredential(new LoginRequest("", "")));
    }

    @Test
    void emptyConstructorTest() {
        assertNull(loginService.getUserCredential(new LoginRequest()));
    }
}