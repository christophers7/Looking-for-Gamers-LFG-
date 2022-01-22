package repository.entities;

import javax.persistence.*;

@Entity
@Table(schema = "project_two",name = "lfg_user_profile")
public class UserProfile {
    @Id
    @Column(name = "profileID")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    private int profileID;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    public UserProfile() {
    }

    public UserProfile(int profileID, String firstName, String lastName, String email) {
        this.profileID = profileID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (profileID != that.profileID) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = profileID;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileID=" + profileID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
