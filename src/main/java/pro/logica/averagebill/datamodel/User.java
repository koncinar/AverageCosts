package pro.logica.averagebill.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 14.10.11
 * Time: 15:24
 */
@Entity
public class User implements Serializable {
    
    private String id;
    private String username;
    private String password;
    private String nickname;

    @Id
    @GeneratedValue
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Transient
    public String getDisplayName() {
        return nickname != null
                ? nickname
                : username;
    }

    @Override
    public String toString() {
        return String.format("User[id = '%s', username = '%s'; nickname = '%s']", id, username, nickname);
    }
}
