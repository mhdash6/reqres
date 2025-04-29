package utilities.common;

import java.util.ArrayList;
import java.util.List;


public class TerminalUtils {
    private static final List<Process> runningProcesses = new ArrayList<>();

    public static boolean executeCommand(boolean wait, String... command) {
        boolean success;
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", String.join(" ", command));
            } else {
                processBuilder = new ProcessBuilder("/bin/bash", "-c", String.join(" ", command));
            }
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            if (wait) {
               success= process.waitFor()==0;
            }else {
                runningProcesses.add(process);
                success=true;
            }
            LogsUtil.info("Command executed: " + String.join(" ", command));
            return success;
        } catch (Exception e) {
            LogsUtil.error("Failed to execute command: " + String.join(" ", command) + ". Error: " + e.getMessage());
            return false;
        }
    }


    public static void killAllRunningProcesses() {
        LogsUtil.info("Killing all running processes.");
        for (Process process : runningProcesses) {
            process.destroy();
        }
        runningProcesses.clear();
        LogsUtil.info("All running processes killed successfully.");
    }
}
