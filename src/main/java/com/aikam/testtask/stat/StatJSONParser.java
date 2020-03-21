package com.aikam.testtask.stat;

import org.json.JSONObject;

import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StatJSONParser {
    public StatInterval parse(String input) throws DateTimeParseException {
        JSONObject jsonObject = new JSONObject(input);

        String stringStartDate = jsonObject.getString("startDate");
        String stringEndDate = jsonObject.getString("endDate");

        Date startDate = Date.valueOf(stringStartDate);
        Date endDate = Date.valueOf(stringEndDate);

        return new StatInterval(startDate, endDate);
    }

    String generate(StatInterval interval, List<StatCustomer> customerList, int totalExpenses, int averageExpenses) {
        return "";
    }

    String generateError(String message) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "error");
        jsonObject.put("message", message);

        return jsonObject.toString(1);
    }
}
