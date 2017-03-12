package edu.matc.persistence;


import javax.persistence.*;

/**
 * Created by student on 3/8/17.
 */
@Entity
@Table(name = "brand", schema = "PriceCheckData", catalog = "")
public class BrandEntity {
    private int brandId;
    private String brandName;

    @Id
    @Column(name = "brandId", nullable = false)
    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "brandName", nullable = true)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrandEntity that = (BrandEntity) o;

        if (brandId != that.brandId) return false;
        if (brandName != null ? !brandName.equals(that.brandName) : that.brandName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brandId;
        result = 31 * result + (brandName != null ? brandName.hashCode() : 0);
        return result;
    }
}
