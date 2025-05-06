package listeners;


import utilities.common.DateTime;
import utilities.selenium.driver.WebDriverManager;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.apache.logging.log4j.ThreadContext;
import org.testng.*;
import utilities.common.FilesUtils;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;
import utilities.selenium.helperClasses.ScreenShotUtils;



import static utilities.common.AllureUtils.*;


public class Listeners implements ITestListener, IExecutionListener, TestLifecycleListener, IInvokedMethodListener {

    private String formatStackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement e : t.getStackTrace()) {
            sb.append("    at ").append(e.toString()).append("\n");
        }
        return sb.toString();
    }


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
    public void onExecutionStart() {
        PropertiesUtils.loadProperties();
        FilesUtils.deleteFileContent(Allure_Results_Path);
        FilesUtils.deleteFileContent(ScreenShotUtils.SCREEN_SHOTS_PATH);
    }

    @Override
    public void onExecutionFinish() {
        createReport();
        openReport(renameReport());
        ThreadContext.clearMap();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browserName = method
                    .getTestMethod()
                    .getXmlTest()
                    .getLocalParameters()
                    .get("browser");
            if (browserName == null) {
                browserName = "chrome";
                LogsUtils.warn("No browser specified in test method. Defaulting to: " + browserName);
            }
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

     }
}

