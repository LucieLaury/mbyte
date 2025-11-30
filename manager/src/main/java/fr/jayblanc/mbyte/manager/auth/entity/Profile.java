package fr.jayblanc.mbyte.manager.auth.entity;

import org.apache.commons.codec.digest.DigestUtils;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Profile.findByUsername", query = "SELECT p FROM Profile p WHERE p.username = :username")
})
@Table(indexes = {
        @Index(name = "profile_idx", columnList = "username")
})
public class Profile {

    @Id
    @Column(length = 50)
    private String id;
    @Version
    private long version;
    private String username;
    private String fullname;
    private String email;

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGravatarHash() {
        return DigestUtils.md5Hex(email);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
