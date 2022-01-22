package service.profile.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import presentation.models.UpdateUserProfileRequest;
import service.login.exceptions.InvalidInputException;
import service.login.validation.LoginValidation;

import static org.junit.jupiter.api.Assertions.*;

class ProfileValidationTest {

    private String validFirstName = "validFirstName";
    private String validLastName = "validLastName";
    private String validEmail = "validemail@email.com";
    private String longInput = "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf";

    @Test
    void validateUpdateProfileTest(){
        UpdateUserProfileRequest invalidProfileRequest = new UpdateUserProfileRequest(
                validFirstName,
                validLastName,
                validEmail
        );
        assertDoesNotThrow(() -> ProfileValidation.validateUpdateProfile(invalidProfileRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateUpdateProfileInvalidFirstnameTest(String input){
        UpdateUserProfileRequest invalidProfileRequest = new UpdateUserProfileRequest(
                input,
                validLastName,
                validEmail
        );
        assertThrows(InvalidInputException.class ,() -> ProfileValidation.validateUpdateProfile(invalidProfileRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateUpdateProfileInvalidLastnameTest(String input){
        UpdateUserProfileRequest invalidProfileRequest = new UpdateUserProfileRequest(
                validFirstName,
                input,
                validEmail
        );
        assertThrows(InvalidInputException.class ,() -> ProfileValidation.validateUpdateProfile(invalidProfileRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "username.@domain.com", ".user.name@domain.com", "user-name@domain.com.", "username@.com", "  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidEmailsTest(String input) {
        UpdateUserProfileRequest invalidProfileRequest = new UpdateUserProfileRequest(
                validFirstName,
                validLastName,
                input
        );
        assertThrows(InvalidInputException.class ,() -> ProfileValidation.validateUpdateProfile(invalidProfileRequest));
    }
}