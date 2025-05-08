package utilities.common.jiraReporting;

import io.qameta.allure.Story;
import utilities.common.PropertiesUtils;

import java.io.File;

public class JiraManager {
    private static JiraReporter instance;

    private synchronized static JiraReporter getInstance() {
        if (instance == null) {
            instance = new JiraReporter(PropertiesUtils.getProperty("jiraUrl"),
                    PropertiesUtils.getProperty("jiraEmail"),
                    PropertiesUtils.getProperty("jiraToken"));
        }
        return instance;
    }

    public static String createSummary(String testName, Story story) {
        if (story != null) {
            return "Automated Test Failure: " + story.value();
        } else {
            return "Automated Test Failure: " + testName;
        }
    }


    public static String createDescription(String errorMessage) {
        return String.format(
                        "Assertion failed:%n> %s%n%n" +
                        "See attached screenshot.",
                errorMessage
        );
    }

    public static void reportFailure(String projectKey, String summary, String description, File screenshot) {
        if (screenshot != null) {
           getInstance().reportBug(projectKey, summary, description,"ash" , screenshot.getPath());
        } else {
            getInstance().reportBug(projectKey, summary, description, "ash");
        }
    }
}
