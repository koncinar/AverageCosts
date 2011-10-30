package pro.logica.averagebill.persistence;

import pro.logica.averagebill.datamodel.CostType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 20.10.11
 * Time: 1:31
 */
@Named
public class CostTypeConverter implements Converter {
    @Inject private List<CostType> costTypes;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (costTypes != null) {
            System.out.println(costTypes.size());
        } else {
            System.out.println("costTypes is null");
        }
        for (CostType costType : costTypes) {
            if (costType.getName() != null && costType.getName().equals(s)) {
                System.out.println(String.format("Returning costType: %s", costType.getName()));
                return costType;
            }
        }
        System.out.println("Returning costType: null");
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((CostType) o).getName();
    }
}
