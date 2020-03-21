package com.aikam.testtask.stat;

import java.util.List;

public class StatCustomer {
    private int id;
    private String name;
    private List<StatPurchase> purchases;

    public StatCustomer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<StatPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<StatPurchase> purchases) {
        this.purchases = purchases;
    }

    public int getId() {
        return id;
    }
}
