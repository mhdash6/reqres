package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utilities.common.LogsUtils;
import utilities.common.assertions.AssertionManager;
import utilities.selenium.driver.WebDriverManager;

import static utilities.common.AllureUtils.attachLogs;

public class MethodInvocationListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        String browser = testResult.getTestContext()
                .getCurrentXmlTest()
                .getLocalParameters()
                .getOrDefault("browser", "chrome");
        ThreadContext.put("browser", browser);
        if (method.isTestMethod()) {
            WebDriverManager.initializeDriver(browser);
            LogsUtils.info("Initializing driver for test: " + method.getTestMethod().getMethodName()  + " | Browser: " + browser);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        AssertionManager.assertAll(testResult);
        WebDriverManager.closeDriver();
        attachLogs(testResult);
    }
}
