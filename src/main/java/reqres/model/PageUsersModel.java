package reqres.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PageUsersModel {
    @JsonProperty("page")
    public Integer page;
    @JsonProperty("per_page")
    public Integer perPage;
    @JsonProperty("total")
    public Integer total;
    @JsonProperty("total_pages")
    public Integer totalPages;
    @JsonProperty("data")
    public List<Data> data;
    @JsonProperty("support")
    public SingleUserModel.Support support;



    public static class Data {
        @JsonProperty("id")
        public Integer id;

        @JsonProperty("email")
        public String email;

        @JsonProperty("first_name")
        public String firstName;

        @JsonProperty("last_name")
        public String lastName;

        @JsonProperty("avatar")
        public String avatar;
    }

    public static class Support {
        @JsonProperty("url")
        public String url;
        @JsonProperty("text")
        public String text;
    }
}
