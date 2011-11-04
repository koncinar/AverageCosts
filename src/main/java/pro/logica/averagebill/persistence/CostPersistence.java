package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.Cost;
import pro.logica.averagebill.datamodel.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 23.10.11
 * Time: 20:47
 */
//@Stateful
//@Model
@ApplicationScoped
@Named
public class CostPersistence {

@PersistenceContext
private EntityManager em;

    private List<Cost> costs;
    @Inject private Event<Cost> costEvent;
    @Inject @Named("user") private User user;

    @Produces
    @Named("costs")
    public List<Cost> getCosts() {
        return costs;
    }

    public List<Cost> getCostsOfUser() {
        List<Cost> costs1 = new ArrayList<Cost>();
        if (user == null) {
            System.out.println("user = null");
            return costs;
        }
        for (Cost cost : costs) {
            if (cost.getUser() != null &&
                    user.getId().equals(cost.getUser().getId())) {
                costs1.add(cost);
            }
        }
        System.out.println(String.format("CostPersistence.getCostsOfUser() returning %s costs for %s.", costs1.size(), user));
        return costs1;
    }

    public int getNumberOfCosts() {
        return costs == null ? 0 : costs.size();
    }

    private void retrieveCosts(@Observes Cost cost) {
        retrieveCosts();
    }

    @PostConstruct
    public void retrieveCosts() {
        Query query = em.createQuery("select c from Cost c order by c.creationData");
        costs = query.getResultList();
    }

    public void deleteCost(Cost cost) {
        em.remove(cost);
        costEvent.fire(cost);
    }
}
