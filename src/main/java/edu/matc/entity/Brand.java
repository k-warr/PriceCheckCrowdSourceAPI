package edu.matc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by student on 3/13/17.
 */
@Entity
public class Brand {
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
    @Column(name = "brandName", nullable = true, length = 128)
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

        Brand brand = (Brand) o;

        if (brandId != brand.brandId) return false;
        if (brandName != null ? !brandName.equals(brand.brandName) : brand.brandName != null)
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
