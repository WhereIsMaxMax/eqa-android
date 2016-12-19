package com.whrsmxmx.eqa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Max on 17.12.2016.
 */

public class DefaultDateFormatter {
    public static String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
        return sdf.format(date);
    }
}
