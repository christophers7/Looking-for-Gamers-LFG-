package service.login.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import presentation.models.login.LoginRequest;
import presentation.models.login.NewUserCredentialsRequest;
import repository.DAO.implementation.UserCredentialsDao;
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

    private NewUserCredentialsRequest newUserCredentialRequested;
    private UserCredential newUserCredential;
    private UserCredential requestedUserCredential;

    private UserProfile storedUserProfile;


   @BeforeAll
   void setUp() {
    MockitoAnnotations.openMocks(this);

    loginRequest = new LoginRequest(
            "username", "password"
    );

    userCredRequest = new UserCredential(
            0, loginRequest.getUsername(), loginRequest.getPassword()
    );

    storedUserProfile = new UserProfile(
            1, storedUserCredential, "firstName", "lastName", "email"
    );


    storedUserCredential = new UserCredential(
            1, loginRequest.getUsername(), loginRequest.getPassword()
    );

    newUserCredentialRequested = new NewUserCredentialsRequest(
            "newUser", "newPass", "newEmail@email.com"
    );

    requestedUserCredential = new UserCredential(
            0,
            newUserCredentialRequested.getUsername(),
            newUserCredentialRequested.getPassword()
    );

    newUserCredential = new UserCredential(
            2, newUserCredentialRequested.getUsername(), newUserCredentialRequested.getPassword()
    );

    Mockito.when(userCredDao.getUser(userCredRequest)).thenReturn(storedUserCredential);
    Mockito.when(userCredDao.getUser(new UserCredential(newUserCredential.getUserID(), "", ""))).thenReturn(newUserCredential);
    Mockito.when(userCredDao.createUser(requestedUserCredential)).thenReturn(2);

    loginService = new LoginService(userCredDao);
   }


    void getUserCredentialSuccessTest(String input) {
       assertEquals(storedUserCredential, loginService.getUserCredentialFromLogin(loginRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void invalidInputsUsernameTest(String input) {
       assertNull(loginService.getUserCredentialFromLogin(new LoginRequest(input, "pass")));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void invalidInputsPasswordTest(String input) {
        assertNull(loginService.getUserCredentialFromLogin(new LoginRequest("user", input)));
    }

    @Test
    void newUserCredentialTest() {
       assertEquals(2, loginService.newUserCredential(newUserCredentialRequested));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void invalidUserCredentialReturnNegativeTest(String input){
        assertEquals(-1, loginService.newUserCredential(new NewUserCredentialsRequest(input, input, input)));
    }

    @Test
    void newAccountTest() {
       assertEquals(newUserCredential, loginService.newAccount(newUserCredentialRequested));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void newAccountNullInvalidTest(String input) {
        assertNull(loginService.newAccount(new NewUserCredentialsRequest(input, input, input)));
    }


}