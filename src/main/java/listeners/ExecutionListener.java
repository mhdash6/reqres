package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.IExecutionListener;
import utilities.common.AllureUtils;
import utilities.common.FilesUtils;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;
import utilities.common.assertions.AssertionManager;
import utilities.selenium.helperClasses.ScreenShotUtils;

import static utilities.common.AllureUtils.*;

public class ExecutionListener implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        PropertiesUtils.loadProperties();
        FilesUtils.createDirectoryIfNeeded(LogsUtils.LOG_FILE_PATH);
        FilesUtils.createDirectoryIfNeeded(AllureUtils.Allure_Results_Path);
        FilesUtils.createDirectoryIfNeeded(ScreenShotUtils.SCREEN_SHOTS_PATH);
        FilesUtils.createDirectoryIfNeeded(AllureUtils.Allure_Report_Path);
        FilesUtils.deleteFileContent(AllureUtils.Allure_Results_Path,"executor.json");
        FilesUtils.deleteFileContent(ScreenShotUtils.SCREEN_SHOTS_PATH);
        AllureUtils.setEnvironmentVariables();
    }

    @Override
    public void onExecutionFinish() {
        if ("local".equalsIgnoreCase(PropertiesUtils.getProperty("executionType"))) {
            createReport();
            openReport(renameReport());
        }
        ThreadContext.clearMap();
        AssertionManager.clearSoftAssert();
    }
}
