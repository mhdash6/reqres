package utilities;


import java.io.OutputStream;
import java.io.PrintStream;

public class RestAssuredLogStream extends PrintStream {

    public RestAssuredLogStream() {
        super(new OutputStream() {
            private final StringBuilder sb = new StringBuilder();

            @Override
            public void write(int b) {
                if (b == '\n') {
                    String logLine = sb.toString();
                    System.out.println(logLine);
                    LogsUtils.info(logLine);
                    sb.setLength(0);
                } else {
                    sb.append((char) b);
                }
            }
        }, true);
    }
}
