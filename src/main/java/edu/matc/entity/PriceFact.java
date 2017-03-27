package edu.matc.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by student on 3/13/17.
 */
@Entity
@Table(name = "priceFact")
public class PriceFact {
    private long factId;
    private int userId;
    private BigDecimal priceAmount;
    private int storeId;
    private int itemId;
    private Timestamp factDateTime;
    private Integer brandId;
    private User userByUserId;
    private Store storeByStoreId;
    private Item itemByItemId;
    private Brand brandByBrandId;

    @Id
    @Column(name = "factId", nullable = false)
    public long getFactId() {
        return factId;
    }

    public void setFactId(long factId) {
        this.factId = factId;
    }

    @Basic
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "priceAmount", nullable = false, precision = 2)
    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    @Basic
    @Column(name = "storeId", nullable = false)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "itemId", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "factDateTime", nullable = false)
    public Timestamp getFactDateTime() {
        return factDateTime;
    }

    public void setFactDateTime(Timestamp factDateTime) {
        this.factDateTime = factDateTime;
    }

    @Basic
    @Column(name = "brandId", nullable = true)
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceFact priceFact = (PriceFact) o;

        if (factId != priceFact.factId) return false;
        if (userId != priceFact.userId) return false;
        if (storeId != priceFact.storeId) return false;
        if (itemId != priceFact.itemId) return false;
        if (priceAmount != null ? !priceAmount.equals(priceFact.priceAmount) : priceFact.priceAmount != null)
            return false;
        if (factDateTime != null ? !factDateTime.equals(priceFact.factDateTime) : priceFact.factDateTime != null)
            return false;
        if (brandId != null ? !brandId.equals(priceFact.brandId) : priceFact.brandId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (factId ^ (factId >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (priceAmount != null ? priceAmount.hashCode() : 0);
        result = 31 * result + storeId;
        result = 31 * result + itemId;
        result = 31 * result + (factDateTime != null ? factDateTime.hashCode() : 0);
        result = 31 * result + (brandId != null ? brandId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable =
            false, insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "storeId", nullable
            = false , insertable = false, updatable = false)
    public Store getStoreByStoreId() {
        return storeByStoreId;
    }

    public void setStoreByStoreId(Store storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", nullable =
            false, insertable = false, updatable = false)
    public Item getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(Item itemByItemId) {
        this.itemByItemId = itemByItemId;
    }

    @ManyToOne
    @JoinColumn(name = "brandId", referencedColumnName = "brandId",
            insertable = false, updatable = false)
    public Brand getBrandByBrandId() {
        return brandByBrandId;
    }

    public void setBrandByBrandId(Brand brandByBrandId) {
        this.brandByBrandId = brandByBrandId;
    }
}
