package edu.matc.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by student on 3/13/17.
 */
@Entity
@Table(name = "store")
public class Store {
    private int storeId;
    private String storeName;
    private BigDecimal longtitude;
    private BigDecimal latitude;
    private String storeAddress;

    @Id
    @GeneratedValue
    @Column(name = "storeId", nullable = false)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "storeName", nullable = false, length = 128)
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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
    @Column(name = "storeAddress", nullable = false, length = 128)
    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (storeId != store.storeId) return false;
        if (storeName != null ? !storeName.equals(store.storeName) : store.storeName != null)
            return false;
        if (longtitude != null ? !longtitude.equals(store.longtitude) : store.longtitude != null)
            return false;
        if (latitude != null ? !latitude.equals(store.latitude) : store.latitude != null)
            return false;
        if (storeAddress != null ? !storeAddress.equals(store.storeAddress) : store.storeAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (longtitude != null ? longtitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (storeAddress != null ? storeAddress.hashCode() : 0);
        return result;
    }
}
