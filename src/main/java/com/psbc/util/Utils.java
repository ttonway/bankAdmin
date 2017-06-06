package com.psbc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ttonway on 17-6-6.
 */
public class Utils {

    public static String currentTimeStr() {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        return formatter.format(new Date());
    }
}
