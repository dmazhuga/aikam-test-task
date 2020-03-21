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

    String generate(List<SuccessfulSearch> searches) {
        JSONObject jsonRoot = new JSONObject();

        jsonRoot.put("type", "search");

        JSONArray jsonSearches = new JSONArray();
        for (SuccessfulSearch search : searches) {
            JSONObject jsonSearch = new JSONObject();

            SearchCriterion criterion = search.getCriterion();
            List<Customer> searchResult = search.getResult();

            JSONObject jsonCriterion = new JSONObject();
            JSONArray jsonCustomers = new JSONArray();

            if (criterion instanceof LastNameSearchCriterion) {
                LastNameSearchCriterion lnCriterion = (LastNameSearchCriterion) criterion;

                jsonCriterion.put("lastName", lnCriterion.getLastName());
            } else if (criterion instanceof ProductSearchCriterion) {
                ProductSearchCriterion prCriterion = (ProductSearchCriterion) criterion;

                jsonCriterion.put("productName", prCriterion.getProductName());
                jsonCriterion.put("minTimes", prCriterion.getMinTimes());
            } else if (criterion instanceof IntervalSearchCriterion) {
                IntervalSearchCriterion inCriterion = (IntervalSearchCriterion) criterion;

                jsonCriterion.put("minExpenses", inCriterion.getMinExpenses());
                jsonCriterion.put("maxExpenses", inCriterion.getMaxExpenses());
            } else if (criterion instanceof  BadCustomersSearchCriterion) {
                BadCustomersSearchCriterion badCriterion = (BadCustomersSearchCriterion) criterion;

                jsonCriterion.put("badCustomer", badCriterion.getCount());
            }

            for (Customer customer : searchResult) {
                JSONObject jsonCustomer = new JSONObject();

                jsonCustomer.put("lastName", customer.getLastName());
                jsonCustomer.put("firstName", customer.getFirstName());

                jsonCustomers.put(jsonCustomer);
            }

            jsonSearch.put("criteria", jsonCriterion);
            jsonSearch.put("results", jsonCustomers);

            jsonSearches.put(jsonSearch);
        }

        jsonRoot.put("results", jsonSearches);

        return jsonRoot.toString(1);
    }

    String generateError(String message) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "error");
        jsonObject.put("message", message);

        return jsonObject.toString(1);
    }
}
