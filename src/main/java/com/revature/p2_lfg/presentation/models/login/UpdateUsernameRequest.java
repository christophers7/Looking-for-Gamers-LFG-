package com.revature.p2_lfg.presentation.models.login;


import java.util.Objects;

public class UpdateUsernameRequest {

    String username;

    public UpdateUsernameRequest(String username) {
        this.username = username;
    }

    public UpdateUsernameRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUsernameRequest)) return false;
        UpdateUsernameRequest that = (UpdateUsernameRequest) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "{\"UpdateUsernameRequest\":{"
                + "\"username\":\"" + username + "\""
                + "}}";
    }
}
