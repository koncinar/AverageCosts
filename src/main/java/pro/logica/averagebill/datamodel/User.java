package pro.logica.averagebill.datamodel;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 14.10.11
 * Time: 15:24
 */
@Entity
public class User {
    
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
