package com.aikam.testtask.stat;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StatJSONParser {
    public StatInterval parse(String input) throws DateTimeParseException {
        JSONObject jsonObject = new JSONObject(input);

        String stringStartDate = jsonObject.getString("startDate");
        String stringEndDate = jsonObject.getString("endDate");

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(stringStartDate, dateFormat);
        LocalDate endDate = LocalDate.parse(stringEndDate, dateFormat);

        return new StatInterval(startDate, endDate);
    }

    String generate(StatInterval interval, List<StatCustomer> customerList) {
        return "";
    }

    String generateError(String message) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "error");
        jsonObject.put("message", message);

        return jsonObject.toString();
    }
}
