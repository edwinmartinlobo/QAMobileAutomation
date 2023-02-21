package com.mtaf.utils;
import org.apache.log4j.Logger;


public class Log {

    private static Logger MTAFLog = Logger.getLogger(Log.class.getName());

    public static void INFO(String message){
        MTAFLog.info(message);
    }

    public static void ERROR(String message){
        MTAFLog.error(message);
    }

    public static void WARN(String message){
        MTAFLog.warn(message);
    }

    public static void DEBUG(String message){
        MTAFLog.debug(message);
    }

}
