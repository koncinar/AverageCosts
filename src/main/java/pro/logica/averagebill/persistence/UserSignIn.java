package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.User;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 2.11.11
 * Time: 18:23
 */
@Named
@Model
@SessionScoped
public class UserSignIn implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private String username;
    private String password;
    private String nickname;

    @Named("user")
    @Produces
    @SessionScoped
    private User user;

    public void signIn() {
        User u = new User();
        u.setId(String.valueOf(new Date().getTime()));
        u.setUsername(username);
        u.setPassword(password);
        u.setNickname(nickname);
        em.persist(u);
        em.flush();
        login();
    }

    public String signOut() {
        user = null;
        return "index";
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
//            request.login(this.username, this.password);
            this.user = (User) em.createQuery("select u from User u where u.username = :username and u.password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
//        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
//            context.addMessage(null, new FacesMessage("Unknown login"));
        } catch (NoResultException e) {
            context.addMessage(null, new FacesMessage("No user or wrong password"));
        }
    }

    public boolean isSignedIn() {
        return user != null;
    }

    public User getUser() {
        return user;
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
}
