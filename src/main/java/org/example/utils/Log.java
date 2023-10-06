package org.example.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class Log {


    //Initialize instances of properties files to be used in all tests



    // Initialize Log4j logs

    private static Logger LOG = LogManager.getLogger(Log.class.getName());//

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName){


        LOG.info("****************************************************************************************");

        LOG.info("****************************************************************************************");

        LOG.info("$$$$$$$$$$$$$$$$$$$$$           "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

        LOG.info("****************************************************************************************");

        LOG.info("****************************************************************************************");

    }

    //This is to print log for the ending of the test case

    public static void endTestCase(String sTestCaseName){

        LOG.info("XXXXXXXXXXXXXXXXXXXXXXX      "+"      -E---N---D-     "+ sTestCaseName+ "             XXXXXXXXXXXXXXXXXXXXXX");

        LOG.info("X");

        LOG.info("X");

        LOG.info("X");

        LOG.info("X");

    }

    // Need to create these methods, so that they can be called


    public static void info(String message) {
        LOG.info(message);
    }



}
