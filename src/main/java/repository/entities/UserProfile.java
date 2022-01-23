package repository.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "project_two",name = "lfg_user_profile")
public class UserProfile {
    @Id
    @Column(name ="columnID")
    @GeneratedValue(generator = "auto_increment",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1,name = "lfg_user_profile_columnid_seq",
            sequenceName = "lfg_user_profile_columnid_seq")
    private int columnID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID",referencedColumnName = "userID")
    private UserCredential userID;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    public UserProfile() {
    }

    public UserProfile(int columnID, UserCredential userID, String firstName, String lastName, String email) {
        this.columnID = columnID;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getColumnID() {
        return columnID;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }

    public UserCredential getUserID() {
        return userID;
    }

    public void setUserID(UserCredential userID) {
        this.userID = userID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return columnID == that.columnID && Objects.equals(userID, that.userID) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnID, userID, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "{\"UserProfile\":{"
                + "\"columnID\":\"" + columnID + "\""
                + ", \"userID\":" + userID
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + "}}";
    }
}
