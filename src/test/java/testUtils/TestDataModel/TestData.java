package testUtils.TestDataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestData {

    @JsonProperty("newUser")
    public NewUser newUser;
    @JsonProperty("getUserId")
    public Integer getUserId;
    @JsonProperty("deleteUserId")
    public Integer deleteUserId;
    @JsonProperty("updateUserId")
    public Integer updateUserId;
    @JsonProperty("page")
    public Integer page;


    public static class NewUser {
        @JsonProperty("username")
        public String username;
        @JsonProperty("job")
        public String job;
    }
}
