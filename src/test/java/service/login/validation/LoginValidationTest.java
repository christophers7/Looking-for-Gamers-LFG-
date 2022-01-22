package service.login.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import presentation.models.NewUserCredentialsRequest;
import service.login.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginValidationTest {

    private NewUserCredentialsRequest newUserCredentialsRequest;


    @BeforeAll
    void setUp() {
        newUserCredentialsRequest = new NewUserCredentialsRequest();
    }

    @Test
    void validateNewUserCredentialsSuccessTest() {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail("validemail@gmail.com");
        assertDoesNotThrow(() -> LoginValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @ValueSource(strings = {"username@domain.com", "user.name@domain.com", "user-name@domain.com", "username@domain.co.in", "user_name@domain.com"})
    void validateNewUserCredentialsValidEmailsTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail(input);
        assertDoesNotThrow(() -> LoginValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidUsernamesTest(String input) {
        newUserCredentialsRequest.setUsername(input);
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail("validemail@gmail.com");
        assertThrows(InvalidInputException.class, () -> LoginValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidPasswordsTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword(input);
        newUserCredentialsRequest.setEmail("validemail@gmail.com");
        assertThrows(InvalidInputException.class, () -> LoginValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n","asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsEmptyEmailTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail(input);
        assertThrows(InvalidInputException.class, () -> LoginValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "username.@domain.com", ".user.name@domain.com", "user-name@domain.com.", "username@.com", "  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidEmailsTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail(input);
        assertThrows(InvalidInputException.class, () -> LoginValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

}