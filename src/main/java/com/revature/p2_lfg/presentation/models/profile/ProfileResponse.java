package presentation.models.profile;

import java.util.Objects;

public class ProfileResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String JWT;

    public ProfileResponse(String username, String firstName, String lastName, String email, String JWT) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.JWT = JWT;
    }

    public ProfileResponse() {
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

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileResponse)) return false;
        ProfileResponse that = (ProfileResponse) o;
        return Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(JWT, that.JWT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, email, JWT);
    }

    @Override
    public String toString() {
        return "{\"ProfileResponse\":{"
                + "\"username\":\"" + username + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"JWT\":\"" + JWT + "\""
                + "}}";
    }
}
