package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JSON_Utils";
    private static final String NO_VALUE = "N/A";
    private static final String[] ARRAY_WITH_NO_VALUE = {NO_VALUE};

    private static JSONObject sandwichJson;

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {
            sandwichJson = new JSONObject(json);

            sandwich.setMainName(parseMainName(sandwichJson));

            sandwich.setAlsoKnownAs(parseAlsoKnownAs(sandwichJson));

            sandwich.setPlaceOfOrigin(parseJsonString(sandwichJson, "placeOfOrigin"));

            sandwich.setDescription(parseJsonString(sandwichJson, "description"));

            sandwich.setImage(parseJsonString(sandwichJson, "image"));

            sandwich.setIngredients(parseIngredients(sandwichJson));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static String parseMainName(JSONObject json) throws JSONException {
        JSONObject name = json.getJSONObject("name");
        String mainName = name.getString("mainName");
        return (mainName.length() > 0) ? mainName : NO_VALUE;
    }

    private static String parseJsonString(JSONObject json, String key) throws JSONException {
        String jsonValue = json.getString(key);
        return (jsonValue.length() > 0) ? jsonValue : NO_VALUE;
    }

    private static List<String> parseAlsoKnownAs(JSONObject json) throws JSONException {
        JSONObject name = json.getJSONObject("name");
        JSONArray alsoKnownAsJson = name.getJSONArray("alsoKnownAs");

        if (alsoKnownAsJson.length() > 0) {
            return JsonArrayToStringArrayConverter.exectute(alsoKnownAsJson);
        }

        List<String> placeholderList = new ArrayList<>();
        placeholderList.add(NO_VALUE);
        return placeholderList;
    }

    private static List<String> parseIngredients(JSONObject json) throws JSONException {
        JSONArray ingredients = json.getJSONArray("ingredients");
        return JsonArrayToStringArrayConverter.exectute(ingredients);
    }
}
