package reqres;

import java.util.HashMap;
import java.util.Map;

public class Header {

    public static Map<String, String> getHeadersWithoutAuth() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put(Constants.API_KEY_HEADER, Constants.API_KEY_VALUE);
        return headers;
    }

    public static Map<String, String> getHeaders(String token) {
        Map<String, String> headers = getHeadersWithoutAuth();
        headers.put("Authorization", token);
        return headers;
    }
}
