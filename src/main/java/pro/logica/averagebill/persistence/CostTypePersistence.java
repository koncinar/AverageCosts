package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.CostType;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
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
public class CostTypePersistence {

@PersistenceContext
private EntityManager em;

    private List<CostType> costTypes;

    @Produces
    @Named("costTypes")
    public List<CostType> getCostTypes() {
        return costTypes;
    }

    private void retrieveCostTypes(@Observes CostType costType) {
        retrieveCostTypes();
    }

    @PostConstruct
    public void retrieveCostTypes() {
        System.out.println("Reading CostType's...");
        Query query = em.createQuery("select ct from CostType ct order by ct.name");
        costTypes = query.getResultList();
        System.out.println(String.format("%s CostType's found.", costTypes == null ? 0 : costTypes.size()));
    }
}
