package reqres.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatedUserModel {
    @JsonProperty("name")
    public String name;
    @JsonProperty("job")
    public String job;
    @JsonProperty("updatedAt")
    public String updatedAt;
}
