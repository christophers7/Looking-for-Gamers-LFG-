package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import repository.entities.UserProfile;

import java.util.Base64;

public class JWTUtility {

    private static final byte[] secret = Base64.getDecoder().decode("V1FrSlZd1CDC7XBh2f+sJaG6OW5gxC0LlTQestx8/wk");

    public static String generateJWT(UserProfile userProfile) {
        return Jwts.builder()
                .claim("firstName", userProfile.getFirstName())
                .claim("lastName", userProfile.getLastName())
                .claim("username", userProfile.getUserCredential().getUserLogin())
                .claim("userId", userProfile.getUserCredential().getUserID())
                .claim("account", true)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public static JWTInfo verifyUser(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        Jws<Claims> jws;
        try{
            String updateToken = token.replace("Bearer ", "");
             jws = Jwts.parserBuilder()
                     .require("account", true)
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(updateToken);
            return objectMapper.convertValue(jws.getBody(), JWTInfo.class);
        }catch(JwtException e){
            e.printStackTrace();
            return null;
        }
    }
}
