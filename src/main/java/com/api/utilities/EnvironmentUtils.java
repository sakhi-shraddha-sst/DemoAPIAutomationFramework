package com.api.utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnvironmentUtils {

    private EnvironmentUtils(){}


    /**
     * Detects if the code is running in a Jenkins environment.
     */
    public static boolean isRunningInJenkins() {
        return System.getenv("JENKINS_HOME") != null;
    }

    /**
     * Detects if the system supports GUI-based desktop operations.
     * This returns false in Jenkins or headless environments.
     */
    public static boolean isDesktopSupported() {
        return !isRunningInJenkins() && !GraphicsEnvironment.isHeadless() && Desktop.isDesktopSupported();
    }

    /**
     * Safely opens the Extent Report in the browser, only in GUI-supported environments.
     */
    public static void openExtentReportIfSupported(String reportFilePath) {
        if (isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new File(reportFilePath).toURI());
            } catch (IOException e) {
                System.err.println("Unable to open report: " + e.getMessage());
            }
        } else {
            System.out.println("Skipping opening report — running in Jenkins or headless mode.");
        }
    }


}
