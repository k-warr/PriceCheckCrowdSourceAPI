package edu.matc.persistence;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by student on 3/8/17.
 */
@Entity
@Table(name = "grocery", schema = "PriceCheckData", catalog = "")
public class GroceryEntity {
    private int groceryId;
    private String groceryName;
    private BigDecimal longtitude;
    private BigDecimal latitude;
    private String groceryAddress;

    @Id
    @Column(name = "groceryId", nullable = false)
    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    @Basic
    @Column(name = "groceryName", nullable = false, length = 128)
    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    @Basic
    @Column(name = "longtitude", nullable = false, precision = 9)
    public BigDecimal getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(BigDecimal longtitude) {
        this.longtitude = longtitude;
    }

    @Basic
    @Column(name = "latitude", nullable = false, precision = 9)
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "groceryAddress", nullable = false, length = 128)
    public String getGroceryAddress() {
        return groceryAddress;
    }

    public void setGroceryAddress(String groceryAddress) {
        this.groceryAddress = groceryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroceryEntity that = (GroceryEntity) o;

        if (groceryId != that.groceryId) return false;
        if (groceryName != null ? !groceryName.equals(that.groceryName) : that.groceryName != null)
            return false;
        if (longtitude != null ? !longtitude.equals(that.longtitude) : that.longtitude != null)
            return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null)
            return false;
        if (groceryAddress != null ? !groceryAddress.equals(that.groceryAddress) : that.groceryAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groceryId;
        result = 31 * result + (groceryName != null ? groceryName.hashCode() : 0);
        result = 31 * result + (longtitude != null ? longtitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (groceryAddress != null ? groceryAddress.hashCode() : 0);
        return result;
    }
}
