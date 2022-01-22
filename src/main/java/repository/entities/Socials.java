package repository.entities;

import javax.persistence.*;

@Entity
@Table(schema = "project_two",name = "lfg_socials")
public class Socials {
    @Id
    @Column (name = "socialID")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    private int socialID;
    @Column
    private String platform;

    public Socials() {
    }

    public Socials(int socialID, String platform) {
        this.socialID = socialID;
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Socials socials = (Socials) o;

        if (socialID != socials.socialID) return false;
        return platform != null ? platform.equals(socials.platform) : socials.platform == null;
    }

    @Override
    public int hashCode() {
        int result = socialID;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Socials{" +
                "socialID=" + socialID +
                ", platform='" + platform + '\'' +
                '}';
    }

    public int getSocialID() {
        return socialID;
    }

    public void setSocialID(int socialID) {
        this.socialID = socialID;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
