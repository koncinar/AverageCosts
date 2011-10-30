package pro.logica.averagebill.datamodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 14.10.11
 * Time: 15:19
 */
@Entity
public class CostType {
    private String id;
    private CostType parent;
    private List<CostType> children;
    private String name;
    private String description;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    public CostType getParent() {
        return parent;
    }

    public void setParent(CostType parent) {
        this.parent = parent;
    }

    @OneToMany
    public List<CostType> getChildren() {
        return children;
    }

    public void setChildren(List<CostType> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        CostType costType = (CostType) o;
//
//        if (!id.equals(costType.id)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return id.hashCode();
//    }
}
