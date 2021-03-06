package training.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("bugtracker")
@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "access_level")
    private short accesslevel;

    public int getId() {
        return id;
    }

    public int getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(short accesslevel) {
        this.accesslevel = accesslevel;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(username, userData.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}


