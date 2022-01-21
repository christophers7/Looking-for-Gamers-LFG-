package presentation.models;

import java.util.Objects;

public class NewUserProfileRequest {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String gameUsername;

    public NewUserProfileRequest(int userId, String firstName, String lastName, String email, String gameUsername) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gameUsername = gameUsername;
    }

    public NewUserProfileRequest() {
    }

    @Override
    public String toString() {
        return "{\"NewUserProfileRequest\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"gameUsername\":\"" + gameUsername + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewUserProfileRequest)) return false;
        NewUserProfileRequest that = (NewUserProfileRequest) o;
        return getUserId() == that.getUserId() && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getGameUsername(), that.getGameUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getEmail(), getGameUsername());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
