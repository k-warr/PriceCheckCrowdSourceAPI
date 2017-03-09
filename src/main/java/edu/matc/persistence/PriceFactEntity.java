package edu.matc.persistence;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by student on 3/8/17.
 */
@Entity
@Table(name = "priceFact", schema = "PriceCheckData", catalog = "")
public class PriceFactEntity {
    private long factId;
    private BigDecimal priceAmount;
    private Timestamp factDateTime;

    @Id
    @Column(name = "factId", nullable = false)
    public long getFactId() {
        return factId;
    }

    public void setFactId(long factId) {
        this.factId = factId;
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
    @Column(name = "factDateTime", nullable = false)
    public Timestamp getFactDateTime() {
        return factDateTime;
    }

    public void setFactDateTime(Timestamp factDateTime) {
        this.factDateTime = factDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceFactEntity that = (PriceFactEntity) o;

        if (factId != that.factId) return false;
        if (priceAmount != null ? !priceAmount.equals(that.priceAmount) : that.priceAmount != null)
            return false;
        if (factDateTime != null ? !factDateTime.equals(that.factDateTime) : that.factDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (factId ^ (factId >>> 32));
        result = 31 * result + (priceAmount != null ? priceAmount.hashCode() : 0);
        result = 31 * result + (factDateTime != null ? factDateTime.hashCode() : 0);
        return result;
    }
}
