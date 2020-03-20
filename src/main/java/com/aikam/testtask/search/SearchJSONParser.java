package com.aikam.testtask.search;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class SearchJSONParser {
    List<SearchCriterion> parse(String input) {
        ArrayList<SearchCriterion> criteriaList = new ArrayList<>();

        JSONObject root = new JSONObject(input);
        JSONArray criteriaArray = root.getJSONArray("criterias");
        for (Object object : criteriaArray) {
            JSONObject jsonObject = (JSONObject) object;

            if (!jsonObject.optString("lastName").equals("")) {
                String lastName = jsonObject.getString("lastName");

                criteriaList.add(new LastNameSearchCriterion(lastName));
            } else if (!jsonObject.optString("productName").equals("")) {
                String productName = jsonObject.getString("productName");
                int minTimes = jsonObject.getInt("minTimes");

                criteriaList.add(new ProductSearchCriterion(productName, minTimes));
            } else if (jsonObject.optInt("minExpenses", -1) != -1) {
                int minExpenses = jsonObject.getInt("minExpenses");
                int maxExpenses = jsonObject.getInt("maxExpenses");

                criteriaList.add(new IntervalSearchCriterion(minExpenses, maxExpenses));
            } else if (jsonObject.optInt("badCustomers", -1) != -1) {
                int badCustomers = jsonObject.getInt("badCustomers");

                criteriaList.add(new BadCustomersSearchCriterion(badCustomers));
            }
        }

        return criteriaList;
    }

    String generate() {
        return "{\n}";
    }
}
