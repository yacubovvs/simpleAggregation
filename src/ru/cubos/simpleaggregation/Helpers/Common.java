package ru.cubos.simpleaggregation.Helpers;

public class Common {
    public static int hardParseInt(String string_i, int default_i){
        int i = default_i;
        try {
            default_i = Integer.parseInt(string_i);
        }catch (Exception e){}
        return i;
    }
}
