package com.whrsmxmx.eqa.utils;

/**
 * Created by Max on 17.01.2017.
 */

public class StringsTricks {
    public static String removeLastCharIfNotEmpty(String text){
        if (!text.isEmpty())
            text = text.substring(0, text.length()-1);
        return text;
    }
}
