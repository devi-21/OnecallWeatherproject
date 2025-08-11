package com.api.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        try {
            Path reportsDir = Paths.get("reports");
            if (!Files.exists(reportsDir)) Files.createDirectories(reportsDir);
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setTheme(Theme.STANDARD); // Light theme
            spark.config().setDocumentTitle("OpenWeather API Automation Report");
            spark.config().setReportName("Test Execution");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onFinish(ISuite suite) { if (extent != null) extent.flush(); }
    @Override public void onTestStart(ITestResult result) { test.set(extent.createTest(result.getMethod().getMethodName())); }
    @Override public void onTestSuccess(ITestResult result) { test.get().pass("Passed"); }
    @Override public void onTestFailure(ITestResult result) { test.get().fail(result.getThrowable()); }
    @Override public void onTestSkipped(ITestResult result) {
        if (result.getThrowable()!=null) test.get().skip(result.getThrowable());
        else test.get().skip("Skipped");
    }
}
