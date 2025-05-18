package utilities;

import io.qameta.allure.Allure;
import org.testng.ITestResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.util.Objects.isNull;


public class AllureUtils {
    public static final String Allure_Results_Path = "test_outputs/allure_results";
    public static final String Allure_Report_Path = "test_outputs/allure_report";


    public static void attachLogs(ITestResult result) {
        String testName= result.getMethod().getMethodName();
        String logs = LogsUtils.getCapturedLogs();
        if (!logs.isEmpty()) {
            Allure.addAttachment(testName + " Logs", logs);
        }
        LogsUtils.clearCapturedLogs();
    }

    public static void createReport(){
        boolean successful= TerminalUtils.executeCommand(true,"allure", "generate",Allure_Results_Path, "-o", Allure_Report_Path, "--clean" ,"--single-file");
        if (!successful) {
            LogsUtils.error("Failed to generate allure report");
            return;
        }
        LogsUtils.info("Allure report generated successfully");
    }

    public static void openReport(Path file){

        if ( isNull(file) ) {
            LogsUtils.warn("No allure report found in: " + Allure_Report_Path);
        }
        else {
            LogsUtils.info("Opening allure report: " + file);
            if(System.getProperty("os.name").toLowerCase().contains("win") ){
                TerminalUtils.executeCommand(true,"start","\"\"",String.format("\"%s\"",file));
            } else {
                TerminalUtils.executeCommand(true,"open",String.format("\"%s\"",file));
            }
        }
    }

    public static Path renameReport(){
        Path file = FilesUtils.getLatestFile(Allure_Report_Path);
        if (isNull(file)) {
            LogsUtils.warn("No allure report found in: " + Allure_Report_Path);
            return null;
        }
        Path newFile = Path.of(Allure_Report_Path, "Report "+DateTime.getDateTime()+".html");
        FilesUtils.renameFile(file,newFile);
        LogsUtils.info("Allure report renamed successfully to: " + newFile);
        return newFile;
    }




}
