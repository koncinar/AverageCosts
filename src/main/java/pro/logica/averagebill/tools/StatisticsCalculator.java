package pro.logica.averagebill.tools;

import pro.logica.averagebill.datamodel.Cost;
import pro.logica.averagebill.persistence.CostPersistence;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 30.10.11
 * Time: 23:01
 */
@Named
public class StatisticsCalculator {
    @Inject
    CostPersistence costPersistence;

    public double getAverageCost() {
        List<Cost> costs = costPersistence.getCosts();
        if (isNullOrEmpty(costs)) {
            return 0.;
        }
        double price = 0.0;
        for (Cost cost : costs) {
            price += cost.getPrice();
        }
        return price / costs.size();
    }

    public double getAveragePerPerson() {
        List<Cost> costs = costPersistence.getCosts();
        if (isNullOrEmpty(costs)) {
            return 0.;
        }
        double price = 0.0;
        int n = 0;
        for (Cost cost : costs) {
            price += cost.getPrice();
            n += cost.getAffectedPeople() <= 0 ? 1 : cost.getAffectedPeople();
        }
        return price / n;
    }

    private <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }
}
