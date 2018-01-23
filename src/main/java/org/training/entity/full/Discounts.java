package org.training.entity.full;

import org.training.entity.Entity;

/**
 * Discounts for user
 */
public enum Discounts implements Entity<Integer> {

    /**
     * Discounts depends on user role. Only admin can use this discount
     */
    ADMIN_DISCOUNT(1, "admin discount", 50),
    /**
     * Discount depends on amount of users orders
     */
    BY_ORDER_AMOUNT(2, "by order amount discount", 40);

    private Integer id;
    private String name;
    private Integer valueInPercent;

    Discounts(Integer id, String name, Integer valueInPercent) {
        this.id = id;
        this.name = name;
        this.valueInPercent = valueInPercent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getValueInPercent() {
        return valueInPercent;
    }
}
