package repository.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "project_two", name = "lfg_user_creds")
public class UserCredential {
    @Id
    @Column(name = "userID")
    @GeneratedValue(generator = "lfg_user_creds_user_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_creds_user_id_seq", sequenceName = "lfg_user_creds_user_id_seq")
    private int userID;
    @Column
    private String userLogin;
    @Column
    private String userPass;


    public UserCredential() {
    }

    public UserCredential(int userID, String userLogin, String userPass) {
        this.userID = userID;
        this.userLogin = userLogin;
        this.userPass = userPass;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCredential)) return false;
        UserCredential that = (UserCredential) o;
        return userID == that.userID && Objects.equals(userLogin, that.userLogin) && Objects.equals(userPass, that.userPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userLogin, userPass);
    }

    @Override
    public String toString() {
        return "{\"UserCredential\":{"
                + "\"userID\":\"" + userID + "\""
                + ", \"userLogin\":\"" + userLogin + "\""
                + ", \"userPass\":\"" + userPass + "\""
                + "}}";
    }
}
