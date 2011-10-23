package pro.logica.averagebill.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: rok
 * Date: 14.10.11
 * Time: 15:17
 */
@Entity
public class Cost {
    private String id;

    private double price;
    private CostType costType;
    private int affectedPeople;
    private String comment;
    private Date costDate;
    private User user;
    private CreationData creationData;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public int getAffectedPeople() {
        return affectedPeople;
    }

    public void setAffectedPeople(int affectedPeople) {
        this.affectedPeople = affectedPeople;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCostDate() {
        return costDate;
    }

    public void setCostDate(Date costDate) {
        this.costDate = costDate;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Embedded @NotNull
    public CreationData getCreationData() {
        return creationData;
    }

    public void setCreationData(CreationData creationData) {
        this.creationData = creationData;
    }
}
