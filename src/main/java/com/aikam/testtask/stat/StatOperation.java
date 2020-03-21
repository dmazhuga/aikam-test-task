package com.aikam.testtask.stat;

import java.time.format.DateTimeParseException;

public class StatOperation {
    public String execute(String input) {
        StatJSONParser statJSONParser = new StatJSONParser();

        StatInterval statInterval;
        try {
            statInterval = statJSONParser.parse(input);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return statJSONParser.generateError("Неправильный формат даты");
        }



        return "";
    }
}
