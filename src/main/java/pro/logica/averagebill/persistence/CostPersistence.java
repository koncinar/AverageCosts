package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.Cost;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Produces
    @Named("costs")
    public List<Cost> getCosts() {
        return costs;
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
