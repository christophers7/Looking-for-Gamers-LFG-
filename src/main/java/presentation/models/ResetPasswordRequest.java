package presentation.models;

import java.util.Objects;

public class ResetPasswordRequest {

    private String username;
    private String email;

    public ResetPasswordRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public ResetPasswordRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResetPasswordRequest)) return false;
        ResetPasswordRequest that = (ResetPasswordRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "{\"ResetPasswordRequest\":{"
                + "\"username\":\"" + username + "\""
                + ", \"email\":\"" + email + "\""
                + "}}";
    }
}
