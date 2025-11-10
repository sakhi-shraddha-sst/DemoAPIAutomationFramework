package com.api.listeners;


import com.api.reporting.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");

    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Started => " + result.getMethod().getMethodName());
        logger.info("Description => " + result.getMethod().getDescription());
    ExtentReportManager.startTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Passed => " + result.getMethod().getMethodName());
//       logger.info("Description>> " + result.getMethod().getDescription());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Failed => " + result.getMethod().getMethodName());
        logger.fatal("Description => " + result.getMethod().getDescription());
        logger.fatal("Message => " + result.getThrowable().getMessage());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Skipped => " + result.getMethod().getMethodName());

    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");
        ExtentReportManager.endTest();
    }


}