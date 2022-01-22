package repository.entities;

import javax.persistence.*;

@Entity
@Table(schema = "project_two", name = "lfg_user_creds")
public class UserCredential {
    @Id
    @Column(name = "userID")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_creds_user_id_seq", sequenceName = "lfg_user_creds_user_id_seq")
private int userID;
    @Column
    private String userLogin;
    @Column
    private String userPass;
    @OneToOne
    @JoinColumn(name = "privateID",referencedColumnName = "profileID")
    private UserProfile userProfile;
    @OneToOne
    @JoinColumn(name = "publicID", referencedColumnName = "publicID")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCredential that = (UserCredential) o;

        if (userID != that.userID) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userPass != null ? !userPass.equals(that.userPass) : that.userPass != null) return false;
        if (userProfile != null ? !userProfile.equals(that.userProfile) : that.userProfile != null) return false;
        return publicDetails != null ? publicDetails.equals(that.publicDetails) : that.publicDetails == null;
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (userProfile != null ? userProfile.hashCode() : 0);
        result = 31 * result + (publicDetails != null ? publicDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "userID=" + userID +
                ", userLogin='" + userLogin + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userProfile=" + userProfile +
                ", publicDetails=" + publicDetails +
                '}';
    }
}
