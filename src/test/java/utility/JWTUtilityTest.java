package utility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import repository.entities.PublicDetails;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JWTUtilityTest {

    private UserCredential mockCredential;
    private JWTInfo jsonToken;
    private String JWT;

    @BeforeAll
    void setUp(){
        mockCredential = new UserCredential(
                1,
                "username",
                "password",
                new UserProfile(
                        1,
                        "firstName",
                        "lastName",
                        "email"), new PublicDetails()
        );

        jsonToken = new JWTInfo(
                "firstName",
                "lastName",
                "username",
                1
        );

        JWT = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJmaXJzdE5hbWUiLCJsYXN0TmFtZSI6Imxhc3ROYW1lIiwidXNlcm5hbWUiOiJ1c2VybmFtZSIsInVzZXJJZCI6MSwiYWNjb3VudCI6dHJ1ZX0.OBsS9C--r2fqUGdhoy_yK8ER4WzSX5eUdKJ5is_p7_c";
    }

    @Test
    void generateJWTTest() {
        assertEquals(JWT, JWTUtility.generateJWT(mockCredential));
    }

    @Test
    void verifyUserTest() {
        assertEquals(jsonToken, JWTUtility.verifyUser(JWT));
    }

    @Test
    void malformedJWTVerifyTest(){
        assertNull(JWTUtility.verifyUser("lol"));
    }

    @Test
    void invalidJWTVerifyTest(){
        assertNull(JWTUtility.verifyUser("lol.ol.lol"));
    }
}