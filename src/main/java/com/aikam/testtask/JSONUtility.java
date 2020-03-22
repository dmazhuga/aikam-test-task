package com.aikam.testtask;

import org.json.JSONObject;

public class JSONUtility {
    public static String generateError(String errorMessage) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "error");
        jsonObject.put("message", errorMessage);

        return jsonObject.toString(1);
    }
}
