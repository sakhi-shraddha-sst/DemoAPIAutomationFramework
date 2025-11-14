package com.api.constants;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrameworkConstants {
    private static final String RESOURCESPATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator;

    private static final String EXTENTCONFIGPATH = RESOURCESPATH + "config" + File.separator + "extentreportconfig.xml";

    private static final String EXTENTREPORTFOLDERPATH = "test-output" + File.separator + "reports" + File.separator;
    private static String extentReportFilePath = "";




    public static String createReportFilePath() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss");

        return EXTENTREPORTFOLDERPATH + "APITestReport__" + dtf.format(LocalDateTime.now()) + ".html";

    }

    public static String getExtentReportFilePath() {

        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportFilePath();
        }
        return extentReportFilePath;

    }



    public static String getExtentreportfolderpath() {
        return EXTENTREPORTFOLDERPATH;
    }
    public static String getExtentconfigpath() {
        return EXTENTCONFIGPATH;
    }





}
