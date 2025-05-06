package utils.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({ "username", "password" })
public class LoginTestData {
   public String username;
   public String password;

     public LoginTestData() {}

}
