package presentation.models;

import java.util.Objects;

public class ProfileResponse {

    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String gameUsername;
    private String JWT;

    public ProfileResponse(int userId, String username, String firstName, String lastName, String email, String gameUsername, String JWT) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gameUsername = gameUsername;
        this.JWT = JWT;
    }

    public ProfileResponse() {
    }

    @Override
    public String toString() {
        return "{\"ProfileResponse\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"gameUsername\":\"" + gameUsername + "\""
                + ", \"JWT\":\"" + JWT + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileResponse)) return false;
        ProfileResponse that = (ProfileResponse) o;
        return getUserId() == that.getUserId() && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getGameUsername(), that.getGameUsername()) && Objects.equals(getJWT(), that.getJWT());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getFirstName(), getLastName(), getEmail(), getGameUsername(), getJWT());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGameUsername() {
        return gameUsername;
    }

    public void setGameUsername(String gameUsername) {
        this.gameUsername = gameUsername;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }
}
