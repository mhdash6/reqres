package listeners;


import org.testng.IExecutionListener;
import utilities.AllureUtils;
import utilities.FilesUtils;
import utilities.LogsUtils;

import static utilities.AllureUtils.*;


public class ExecutionListener implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        FilesUtils.createDirectoryIfNeeded(LogsUtils.LOG_FILE_PATH);
        FilesUtils.createDirectoryIfNeeded(AllureUtils.Allure_Results_Path);
        FilesUtils.createDirectoryIfNeeded(AllureUtils.Allure_Report_Path);
        FilesUtils.deleteFileContent(Allure_Results_Path);
    }

    @Override
    public void onExecutionFinish() {
        createReport();
        openReport(renameReport());
    }
}
