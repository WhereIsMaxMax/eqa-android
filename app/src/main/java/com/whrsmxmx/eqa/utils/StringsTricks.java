package com.whrsmxmx.eqa.utils;

import java.util.ArrayList;

/**
 * Created by Max on 17.01.2017.
 */

public class StringsTricks {
    public static String removeLastCharIfNotEmpty(String text){
        if (!text.isEmpty())
            text = text.substring(0, text.length()-1);
        return text;
    }
    public static String removeLastTwoCharsIfNotEmpty(String text){
        if (!text.isEmpty())
            text = text.substring(0, text.length()-2);
        return text;
    }

    public static String stringCSVFromArrayList(ArrayList<String>list){
        String result = "";
        for(String s : list){
            result+=s+", ";
        }
        return removeLastTwoCharsIfNotEmpty(result);
    }
}
