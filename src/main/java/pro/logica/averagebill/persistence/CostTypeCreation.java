package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.CostType;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 23.10.11
 * Time: 20:39
 */
@Stateful
@Model
public class CostTypeCreation {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<CostType> costTypeEventSrc;

    private CostType newCostType;

    @PostConstruct
    public void initNewMember() {
        newCostType = new CostType();
    }

    @Produces
    @Named
    public CostType getNewCostType() {
        return newCostType;
    }

    public void saveCostType() {
        em.persist(newCostType);
        costTypeEventSrc.fire(newCostType);
        initNewMember();
    }

    public void delete(CostType costType) {
        em.remove(em.getReference(CostType.class, costType.getId()));
        costTypeEventSrc.fire(costType);
    }
}
