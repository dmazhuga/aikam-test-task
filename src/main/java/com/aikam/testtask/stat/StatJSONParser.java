package com.aikam.testtask.stat;

import com.aikam.testtask.TestTaskException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class StatJSONParser {
    private static final String intervalErrorMessage = "Неправильно задан интервал";

    public StatInterval parse(String input) {
        JSONObject jsonObject = new JSONObject(input);

        String stringStartDate = jsonObject.getString("startDate");
        String stringEndDate = jsonObject.getString("endDate");

        Date startDate = Date.valueOf(stringStartDate);
        Date endDate = Date.valueOf(stringEndDate);

        StatInterval interval = new StatInterval(startDate, endDate);

        if (interval.totalDays() < 1)
            throw new TestTaskException(intervalErrorMessage);

        return interval;
    }

    String generate(StatInterval interval, List<StatCustomer> customerList, int totalExpenses, int averageExpenses) {
        JSONObject root = new JSONObject();

        root.put("type", "stat");
        root.put("totalDays", interval.totalDays());

        JSONArray jsonCustomers = new JSONArray();
        for (StatCustomer customer : customerList) {
            JSONObject jsonCustomer = new JSONObject();

            jsonCustomer.put("name", customer.getName());

            JSONArray jsonPurchases = new JSONArray();
            for (StatPurchase purchase : customer.getPurchases()) {
                JSONObject jsonPurchase = new JSONObject();

                jsonPurchase.put("name", purchase.getName());
                jsonPurchase.put("expenses", purchase.getExpenses());

                jsonPurchases.put(jsonPurchase);
            }
            jsonCustomer.put("purchases", jsonPurchases);

            jsonCustomers.put(jsonCustomer);
        }

        root.put("customers", jsonCustomers);

        return root.toString(1);
    }
}
