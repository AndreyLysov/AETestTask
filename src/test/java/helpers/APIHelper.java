package helpers;

import data_types.Tweet;
import common.CommonMethods;
import common.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;

public class APIHelper {
    static RequestSpecification httpRequest;
    Response headerRequest;

    static {
        RestAssured.baseURI = Constants.SANDBOX_URI;
        httpRequest = RestAssured.given();
    }

    public void getRequest() {
        System.out.println(httpRequest.get("/Hyderabad").asString());
    }

    public void auth() {
        httpRequest.headers(CommonMethods.jsonFileToListOfHashMaps(
                new File("src/test/resources/twitterAccount.json"))
                .get(0));
        headerRequest = httpRequest
                .when()
                .post("oauth/request_token");
    }

    public Response update(Tweet tweet) {
        HashMap<String, String> temp = new HashMap<>();
        temp.put("status", tweet.getText());
        return httpRequest
                .contentType("application/json")
                .body(temp)
                .when()
                .post("statuses/update");
    }

    public Response getTweet(int id) {
        return httpRequest
                .contentType("application/json")
                .body(new HashMap<>()
                        .put("id", id))
                .get("statuses/show");
    }

    public Response deleteTweet(int id) {
        return httpRequest
                .contentType("application/json")
                .body(new HashMap<>()
                        .put("id", id))
                .delete("statuses/destroy");
    }
}
