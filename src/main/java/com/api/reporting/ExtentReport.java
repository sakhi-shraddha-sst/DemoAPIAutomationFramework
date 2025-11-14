package com.api.reporting;

import com.api.constants.FrameworkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ExtentReport {

    private ExtentReport(){}

    private static ExtentReports report = null;
    public static String extentreportpath = "";

    public static void initReport() throws Exception {

        if(Objects.isNull(report)){

            report = new ExtentReports();

            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath())
                    .viewConfigurer().viewOrder().as(new ViewName[]{
                            ViewName.DASHBOARD, ViewName.TEST, ViewName.LOG
                    }).apply();


            try {
                spark.loadXMLConfig(new File(FrameworkConstants.getExtentconfigpath()));

            }catch (IOException e){
                throw new Exception("Some IO exception occured while reading Extent Config file");
            }

            report.setSystemInfo("Project Name: ", "API Test");
            report.setSystemInfo("API Swagger URI", "http://64.227.160.186:8080/swagger-ui/index.html");
            report.setSystemInfo("Automation Test Engineer", "Sakhi Mali");

            report.attachReporter(spark);
            System.out.println("Report Initiated");
            System.out.println(FrameworkConstants.getExtentReportFilePath());

        }
    }
    public static void createTestName(String testcasename) {

//        ExtentReportManager.createInstance(report.createTest(testcasename));

    }
    public static void flushReports() throws IOException {
        if(Objects.nonNull(report)){
            report.flush();
        }
        Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());


    }


}
