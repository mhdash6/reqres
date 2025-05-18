package testUtils.TestDataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpectedStatusCodes {
    @JsonProperty("update")
    public Integer update;
    @JsonProperty("delete")
    public Integer delete;
    @JsonProperty("create")
    public Integer create;
    @JsonProperty("get")
    public Integer get;
}
