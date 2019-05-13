package com.udacity.sandwichclub.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

class JsonArrayToStringArrayConverter {
    private static List<String> convertedStringList;

    static List<String> exectute(JSONArray jsonArray) throws JSONException {
        convertedStringList = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            convertedStringList.add(jsonArray.getString(i));
        }

        return convertedStringList;
    }
}
