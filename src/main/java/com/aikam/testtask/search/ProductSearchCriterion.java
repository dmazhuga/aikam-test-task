package com.aikam.testtask.search;

public class ProductSearchCriterion implements SearchCriterion {
    private String productName;
    private int minTimes;

    public ProductSearchCriterion(String productName, int minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public int getMinTimes() {
        return minTimes;
    }
}
