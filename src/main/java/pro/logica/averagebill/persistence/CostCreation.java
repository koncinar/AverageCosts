package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.Cost;
import pro.logica.averagebill.datamodel.CreationData;
import pro.logica.averagebill.datamodel.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 23.10.11
 * Time: 20:39
 */
@Named
@Stateful
@Model
public class CostCreation {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Cost> costTypeEventSrc;
    @Inject
    @Named("user")
    private User user;

    private Cost newCost;

    @PostConstruct
    public void initNewMember() {
        newCost = new Cost();
        newCost.setId("" + new Date().getTime());
        CreationData creationData = new CreationData();
        creationData.setIp("1.2.3." + (int)(Math.random() * 10));
        creationData.setNickName("nick " + Math.random());
        newCost.setCreationData(creationData);
    }

    @Produces
    @Named
    public Cost getNewCost() {
        return newCost;
    }

    public void saveCost() {
        newCost.getCreationData().setCreated(new Date());
        newCost.setUser(user);
        System.out.println("Saving cost " + newCost +
                " for user: " + user);
        em.persist(newCost);
        costTypeEventSrc.fire(newCost);
        initNewMember();
    }

    public void delete(Cost cost) {
        em.remove(em.getReference(Cost.class, cost.getId()));
    }
}
