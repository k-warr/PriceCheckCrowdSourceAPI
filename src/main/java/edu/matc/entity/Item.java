package edu.matc.entity;

import javax.persistence.*;

/**
 * Created by student on 3/13/17.
 */
@Entity
@Table(name = "item")
public class Item {
    private int itemId;
    private String itemName;
    private String unit;
    private int unitValue;

    public Item() {
    }

    public Item(String itemName, String unit, int unitValue) {
        this.itemName = itemName;
        this.unit = unit;
        this.unitValue = unitValue;
    }

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

        Item item = (Item) o;

        if (itemId != item.itemId) return false;
        if (unitValue != item.unitValue) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null)
            return false;
        if (unit != null ? !unit.equals(item.unit) : item.unit != null)
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
