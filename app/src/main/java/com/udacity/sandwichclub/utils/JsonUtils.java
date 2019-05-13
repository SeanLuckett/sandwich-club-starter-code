package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {
    private static final String TAG = "JSON_Utils";

    private static JSONObject sandwichJson;

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {
            sandwichJson = new JSONObject(json);

            sandwich.setMainName(parseMainName(sandwichJson));

            sandwich.setAlsoKnownAs(parseAlsoKnownAs(sandwichJson));

            sandwich.setPlaceOfOrigin(sandwichJson.getString("placeOfOrigin"));

            sandwich.setDescription(sandwichJson.getString("description"));

            sandwich.setImage(sandwichJson.getString("image"));

            sandwich.setIngredients(parseIngredients(sandwichJson));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static String parseMainName(JSONObject json) throws JSONException {
        JSONObject name = json.getJSONObject("name");
        return name.getString("mainName");
    }

    private static List<String> parseAlsoKnownAs(JSONObject json) throws JSONException {
        JSONObject name = json.getJSONObject("name");
        JSONArray alsoKnownAsJson = name.getJSONArray("alsoKnownAs");
        return JsonArrayToStringArrayConverter.exectute(alsoKnownAsJson);
    }

    private static List<String> parseIngredients(JSONObject json) throws JSONException {
        JSONArray ingredients = json.getJSONArray("ingredients");
        return JsonArrayToStringArrayConverter.exectute(ingredients);
    }
}
