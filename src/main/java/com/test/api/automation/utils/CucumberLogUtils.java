package com.test.api.automation.utils;

import java.util.logging.Logger;


public class CucumberLogUtils {
    private static Logger Log = Logger.getLogger(CucumberLogUtils.class.getName());

    public CucumberLogUtils(){

    }

    public static void logPass(String msg){
        Log.info("PASS: " +  msg);
    }

    public static void logFail(String msg){
        Log.info("FAIL: " + msg);
    }

    public static void logInfo(String msg){
        Log.info("INFO: "+ msg);
    }

}
