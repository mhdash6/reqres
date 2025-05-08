package utilities.common.assertions;

import org.testng.ITestResult;

public class AssertionManager {
    private static final ThreadLocal<CustomSoftAssert> softAssertContainer =  ThreadLocal.withInitial(CustomSoftAssert::new);

    public static CustomSoftAssert getSoftAssert() {
        return softAssertContainer.get();
    }

    public static void assertAll(ITestResult testResult) {
        getSoftAssert().customAssertAll(testResult);
        clearSoftAssert();
    }

    public static void clearSoftAssert() {
        softAssertContainer.remove();
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        getSoftAssert().assertEquals(actual, expected, message);
    }

    public static void assertEquals(Object actual, Object expected) {
        getSoftAssert().assertEquals(actual, expected);
    }

    public static void assertTrue(boolean condition, String message) {
        getSoftAssert().assertTrue(condition, message);
    }

    public static void assertTrue(boolean condition) {
        getSoftAssert().assertTrue(condition);
    }

    public static void assertFalse(boolean condition, String message) {
        getSoftAssert().assertFalse(condition, message);
    }

    public static void assertFalse(boolean condition) {
        getSoftAssert().assertFalse(condition);
    }
}
