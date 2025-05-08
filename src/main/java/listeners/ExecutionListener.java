package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.IExecutionListener;
import utilities.common.AllureUtils;
import utilities.common.FilesUtils;
import utilities.common.PropertiesUtils;
import utilities.common.assertions.AssertionManager;
import utilities.selenium.helperClasses.ScreenShotUtils;

import static utilities.common.AllureUtils.*;

public class ExecutionListener implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        PropertiesUtils.loadProperties();
        FilesUtils.deleteFileContent(AllureUtils.Allure_Results_Path,"executor.json");
        FilesUtils.deleteFileContent(ScreenShotUtils.SCREEN_SHOTS_PATH);
    }

    @Override
    public void onExecutionFinish() {
        createReport();
        openReport(renameReport());
        ThreadContext.clearMap();
        AssertionManager.clearSoftAssert();
    }
}
