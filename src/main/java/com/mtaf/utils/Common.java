/*********************************************************
 * Created :   Mayur Waghmare
 * Date    :   05-04-2019
 *********************************************************/

package com.mtaf.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    private static final DateFormat df = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss.SSS");

    public static String getCurrentTime()   {
        Date date = new Date();
        return df.format(date).toString();
    }
}
