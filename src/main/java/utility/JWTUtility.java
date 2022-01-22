package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import repository.entities.PublicDetails;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

import java.util.Base64;

import static io.jsonwebtoken.Jwts.parserBuilder;

public class JWTUtility {

    private static final byte[] secret = Base64.getDecoder().decode("V1FrSlZd1CDC7XBh2f+sJaG6OW5gxC0LlTQestx8/wk");

    public static String generateJWT(UserCredential userCredential) {
        return Jwts.builder()
                .claim("firstName", userCredential.getUserProfile().getFirstName())
                .claim("lastName", userCredential.getUserProfile().getLastName())
                .claim("username", userCredential.getUserLogin())
                .claim("userId", userCredential.getUserID())
                .claim("account", true)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public static JWTInfo verifyUser(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        Jws<Claims> jws;
        try{
             jws = Jwts.parserBuilder()
                     .require("account", true)
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(token);
            return objectMapper.convertValue(jws.getBody(), JWTInfo.class);
        }catch(JwtException e){
            e.printStackTrace();
            return null;
        }
    }
}
