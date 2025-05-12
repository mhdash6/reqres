package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.common.LogsUtils;

public class TestResultListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        LogsUtils.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LogsUtils.info("Test run started");
    }

    @Override
    public void onFinish(ITestContext context) {
        LogsUtils.info("Test run finished");
    }
}
