package repository.entities;

import java.util.Objects;

public class UserCredential {

    private int userID;
    private String userLogin;
    private String userPass;
    private UserProfile userProfile;
    private PublicDetails publicDetails;

    public UserCredential() {
    }

    public UserCredential(int userID, String userLogin, String userPass, UserProfile userProfile, PublicDetails publicDetails) {
        this.userID = userID;
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.userProfile = userProfile;
        this.publicDetails = publicDetails;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public PublicDetails getPublicDetails() {
        return publicDetails;
    }

    public void setPublicDetails(PublicDetails publicDetails) {
        this.publicDetails = publicDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCredential)) return false;
        UserCredential that = (UserCredential) o;
        return userID == that.userID && Objects.equals(userLogin, that.userLogin) && Objects.equals(userPass, that.userPass) && Objects.equals(userProfile, that.userProfile) && Objects.equals(publicDetails, that.publicDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userLogin, userPass, userProfile, publicDetails);
    }

    @Override
    public String toString() {
        return "{\"UserCredential\":{"
                + "\"userID\":\"" + userID + "\""
                + ", \"userLogin\":\"" + userLogin + "\""
                + ", \"userPass\":\"" + userPass + "\""
                + ", \"userProfile\":" + userProfile
                + ", \"publicDetails\":" + publicDetails
                + "}}";
    }
}
