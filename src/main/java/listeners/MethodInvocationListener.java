package listeners;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utilities.RestAssuredLogStream;


import java.io.PrintStream;

import static utilities.AllureUtils.attachLogs;

public class MethodInvocationListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            PrintStream logStream = new RestAssuredLogStream();
            RestAssured.replaceFiltersWith(
                    new RequestLoggingFilter(logStream),
                    new ResponseLoggingFilter(logStream)
            );
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        attachLogs(testResult);
    }
}
