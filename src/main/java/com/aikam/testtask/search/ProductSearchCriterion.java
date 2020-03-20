package com.aikam.testtask.search;

public class ProductSearchCriterion implements SearchCriterion {
    private static final String functionName = "customers_product_search";

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

    @Override
    public String getQuery() {
        return "SELECT * FROM " + functionName + "(\'" + productName + "\', " + minTimes + ")";
    }
}
