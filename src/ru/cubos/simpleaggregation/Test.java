package ru.cubos.simpleaggregation;

import ru.cubos.simpleaggregation.Helpers.APIConnector;
import ru.cubos.simpleaggregation.Helpers.JSONParser;

public class Test {
    public static void main(String[] args) {
        APIConnector apiConnector = new APIConnector("http://mdlp.ddns.net/", "2624228900");
        String resultText = apiConnector.insertItemAggregation("00146063240000000073", "010460632400083021YGZjV2dHybmpl91EE0692DBh5KDWrONOeBUFttGR3h6vutRV3UG03rKY8qi7PUpQ=");

        String result = JSONParser.getElementOfJSON(resultText, "result");
        if(result.substring(0,5).equals("error")){
            String message = JSONParser.getElementOfJSON(resultText, "message");
        }


        return;
    }
}
