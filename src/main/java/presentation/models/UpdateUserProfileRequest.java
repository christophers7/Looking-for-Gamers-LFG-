package presentation.models;

import java.util.Objects;

public class UpdateUserProfileRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String gameUsername;

    public UpdateUserProfileRequest(String firstName, String lastName, String email, String gameUsername) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gameUsername = gameUsername;
    }

    @Override
    public String toString() {
        return "{\"UpdateUserProfileRequest\":{"
                + "\"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"gameUsername\":\"" + gameUsername + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUserProfileRequest)) return false;
        UpdateUserProfileRequest that = (UpdateUserProfileRequest) o;
        return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getGameUsername(), that.getGameUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getEmail(), getGameUsername());
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
