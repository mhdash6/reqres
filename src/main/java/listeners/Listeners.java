package listeners;


import utilities.selenium.driver.WebDriverManager;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.apache.logging.log4j.ThreadContext;
import org.testng.*;
import utilities.common.FilesUtils;
import utilities.common.LogsUtil;
import utilities.common.PropertiesUtils;
import utilities.selenium.helperClasses.ScreenShotUtils;

import static utilities.common.AllureUtils.*;


public class Listeners implements ITestListener, IExecutionListener, TestLifecycleListener, IInvokedMethodListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogsUtil.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test failed: " + result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test skipped: " + result.getName());
    }


    @Override
    public void onExecutionStart() {
        PropertiesUtils.loadProperties();
        FilesUtils.deleteFileContent(Allure_Results_Path);
        FilesUtils.deleteFileContent(ScreenShotUtils.SCREEN_SHOTS_PATH);
    }

    @Override
    public void onExecutionFinish() {
        createReport();
        openReport(renameReport());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browserName = method
                    .getTestMethod()
                    .getXmlTest()
                    .getLocalParameters()
                    .get("browser");
             WebDriverManager.initializeDriver(browserName);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriverManager.closeDriver();
        attachLogs(testResult);
    }

    @Override
    public void afterTestStart(TestResult result) {
    }

    @Override
    public void beforeTestStop(TestResult result) {
    }

     @Override
     public void onStart(ITestContext context) {
         ThreadContext.put("browser", context.getCurrentXmlTest().getLocalParameters().get("browser"));
     }
     @Override
     public void onFinish(ITestContext context) {
         ThreadContext.clearMap();
     }
}

