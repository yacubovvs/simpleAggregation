package ru.cubos.simpleaggregation.Helpers;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    public static String getElementOfJSON(String json, String ElementName){
        try{
            JSONObject jsonAnswer = new JSONObject(json);
            return jsonAnswer.getString(ElementName);
        } catch (JSONException e) {
            return "";
        }
    }
}
