package com.api.reporting;

import com.api.constants.FrameworkConstants;
import com.api.utilities.EnvironmentUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ExtentReportManager {

private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();
//private static ExtentReports extent;

    private static ExtentReports extent = null;
    private static String extentreportpath = "";

    String fileName = generateReportFileName();



//public static ExtentReports createInstance(){
//    if(extent==null){
//        String fileName = generateReportFileName();
//
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/reports/" + fileName);
//
//        sparkReporter.config().setTheme(Theme.DARK);
//        sparkReporter.config().setDocumentTitle("API Test Report");
//        sparkReporter.config().setReportName("REST API Test Results");
//
//        System.out.println(fileName);
//
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        extent.setSystemInfo("Environment", "Test");
//        extent.setSystemInfo("User" , System.getProperty("user.name"));
//
//    }
//    return extent;
//
//}


    private static ExtentReports createInstance() throws Exception {

        if (Objects.isNull(extent)) {

            extent = new ExtentReports();

            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath())
                    .viewConfigurer().viewOrder().as(new ViewName[]{
                            ViewName.DASHBOARD, ViewName.TEST, ViewName.LOG
                    }).apply();


            try {
                spark.loadXMLConfig(new File(FrameworkConstants.getExtentconfigpath()));

            } catch (IOException e) {
                throw new Exception("Some IO exception occured while reading Extent Config file");
            }

            extent.setSystemInfo("Project Name: ", "API Test");
            extent.setSystemInfo("API Swagger URI", "http://64.227.160.186:8080/swagger-ui/index.html");
            extent.setSystemInfo("Automation Test Engineer", "Sakhi Mali");

            extent.attachReporter(spark);
            System.out.println("Report Initiated");
            System.out.println(FrameworkConstants.getExtentReportFilePath());


        }
return extent;
    }


    private static String generateReportFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss");
    return "APITestReport_" + dtf.format(LocalDateTime.now()) + ".html";
    }

    public static void startTest(String testName) throws Exception {
    ExtentTest extentTest = createInstance().createTest(testName);
    exTest.set(extentTest);
    }


    public static void logExeRequest(FilterableRequestSpecification requestSpec) {

        if(exTest.get()==null) return;

    StringBuilder requestDetails = new StringBuilder();
    requestDetails.append("<pre>");
    requestDetails.append("Request Method: " ).append(requestSpec.getMethod()).append("\n");
    requestDetails.append("Request URI: " ).append(requestSpec.getURI()).append("\n");
    requestDetails.append("Request Headers: " ).append("\n");

    for(Header header : requestSpec.getHeaders()){
            requestDetails.append("    ").append(header.getName()).append(": ")
                    .append(header.getValue()).append("\n");
    }

    if(requestSpec.getBody() != null) {
        requestDetails.append("Request Body: ").append("\n");
        requestDetails.append(requestSpec.getBody().toString());
    }
    requestDetails.append("</pre>");

    exTest.get().log(Status.INFO, "Request Details: " + requestDetails.toString());

    }

    public static void logExeResponse(Response response) {

        if(exTest.get()==null) return;

        StringBuilder responseDetails = new StringBuilder();
        responseDetails.append("<pre>");
        responseDetails.append("Response Status: " ).append(response.getStatusCode()).append("\n");
        responseDetails.append("Response Headers: " ).append("\n");



        for(Header header : response.getHeaders()){
            responseDetails.append("    ").append(header.getName()).append(": ")
                    .append(header.getValue()).append("\n");
        }

        responseDetails.append("Response Body: ").append("\n");
        responseDetails.append(response.getBody().prettyPrint());
        responseDetails.append("</pre>");

        exTest.get().log(Status.INFO, "Response Details: " + responseDetails.toString());

        // Log Based on the Status Code
        if(response.getStatusCode() >= 200 && response.getStatusCode() < 300 ){
            exTest.get().pass("Request Completed Successfully!!");
        }else {
            exTest.get().fail("Request failed with Status Code : " + response.getStatusCode());

        }

    }

    public static void endTest()  {

        if(extent != null)
             extent.flush();

//        Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
        EnvironmentUtils.openExtentReportIfSupported(FrameworkConstants.getExtentReportFilePath());


    }


    public static void logInfo(String message){

        try {
            if(exTest.get() != null){
                exTest.get().log(Status.INFO, message);
            }else {
                System.out.println("ExtentTest is null. Call startTest() before logging.");
            }
        } catch (Exception e) {
            System.out.println("Failed to log to Extent: " + e.getMessage());
        }

    }
}





/* Existing working reportManager
private static ExtentReports extent;
private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

public static ExtentReports createInstance(){
    if(extent==null){
        String fileName = generateReportFileName();

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/reports/" + fileName);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("API Test Report");
        sparkReporter.config().setReportName("REST API Test Results");

        System.out.println(fileName);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User" , System.getProperty("user.name"));

    }
    return extent;

}

    private static String generateReportFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss");
    return "APITestReport_" + dtf.format(LocalDateTime.now()) + ".html";
    }

    public static void startTest(String testName){
    ExtentTest extentTest = createInstance().createTest(testName);
    test.set(extentTest);
    }


    public static void logExeRequest(FilterableRequestSpecification requestSpec) {
    StringBuilder requestDetails = new StringBuilder();
    requestDetails.append("<pre>");
    requestDetails.append("Request Method: " ).append(requestSpec.getMethod()).append("\n");
    requestDetails.append("Request URI: " ).append(requestSpec.getURI()).append("\n");
    requestDetails.append("Request Headers: " ).append("\n");

    for(Header header : requestSpec.getHeaders()){
            requestDetails.append("    ").append(header.getName()).append(": ")
                    .append(header.getValue()).append("\n");
    }

    if(requestSpec.getBody() != null) {
        requestDetails.append("Request Body: ").append("\n");
        requestDetails.append(requestSpec.getBody().toString());
    }
    requestDetails.append("</pre>");

    test.get().log(Status.INFO, "Request Details: " + requestDetails.toString());

    }

    public static void logExeResponse(Response response) {

        StringBuilder responseDetails = new StringBuilder();
        responseDetails.append("<pre>");
        responseDetails.append("Response Status: " ).append(response.getStatusCode()).append("\n");
        responseDetails.append("Response Headers: " ).append("\n");



        for(Header header : response.getHeaders()){
            responseDetails.append("    ").append(header.getName()).append(": ")
                    .append(header.getValue()).append("\n");
        }

        responseDetails.append("Response Body: ").append("\n");
        responseDetails.append(response.getBody().prettyPrint());
        responseDetails.append("</pre>");

        test.get().log(Status.INFO, "Response Details: " + responseDetails.toString());

        // Log Based on the Status Code
        if(response.getStatusCode() >= 200 && response.getStatusCode() < 300 ){
            test.get().pass("Request Completed Successfully!!");
        }else {
            test.get().fail("Request failed with Status Code : " + response.getStatusCode());

        }

    }

    public static void endTest(){
       if(extent!= null){
           extent.flush();
       }
    }
*


**/




