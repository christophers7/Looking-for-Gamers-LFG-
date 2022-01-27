package com.revature.p2_lfg.presentation.models.login;

import java.util.Objects;

public class NewUserCredentialsRequest {

    private String username;
    private String password;
    private String email;

    public NewUserCredentialsRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public NewUserCredentialsRequest() {
    }

    @Override
    public String toString() {
        return "{\"NewUserCredentialsRequest\":{"
                + "\"username\":\"" + username + "\""
                + ", \"password\":\"" + password + "\""
                + ", \"email\":\"" + email + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewUserCredentialsRequest)) return false;
        NewUserCredentialsRequest that = (NewUserCredentialsRequest) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getEmail());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
