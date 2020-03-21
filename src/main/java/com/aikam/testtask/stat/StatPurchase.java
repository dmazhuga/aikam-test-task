package com.aikam.testtask.stat;

public class StatPurchase {
    private String name;
    private int expenses;

    public StatPurchase(String name, int expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public int getExpenses() {
        return expenses;
    }
}
