package edu.matc.persistence;


import javax.persistence.*;

/**
 * Created by student on 3/8/17.
 */
@Entity
@Table(name = "item", schema = "PriceCheckData", catalog = "")
public class ItemEntity {
    private int itemId;
    private String itemName;
    private String unit;
    private int unitValue;

    @Id
    @Column(name = "itemId", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "itemName", nullable = false, length = 128)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "unit", nullable = false, length = 64)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "unitValue", nullable = false)
    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (itemId != that.itemId) return false;
        if (unitValue != that.unitValue) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null)
            return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + unitValue;
        return result;
    }
}
