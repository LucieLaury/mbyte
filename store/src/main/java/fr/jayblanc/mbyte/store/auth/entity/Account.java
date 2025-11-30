package fr.jayblanc.mbyte.store.auth.entity;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Account {

    private String id;
    private String username;
    private String fullname;
    private String email;
    private boolean owner;
    private Locale locale;
    private List<String> roles = new ArrayList<>();

    public Account() {
    }

    public Account(String id, String username, String fullname, String email, boolean owner) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.owner = owner;
        this.locale = Locale.getDefault();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", owner=" + owner +
                ", locale=" + locale +
                ", roles=" + roles +
                '}';
    }
}
