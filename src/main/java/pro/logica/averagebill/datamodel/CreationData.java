package pro.logica.averagebill.datamodel;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * User: rok
 * Date: 14.10.11
 * Time: 15:26
 */
@Embeddable
public class CreationData {
    private Date created;
    private String ip;
    private String nickName;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
