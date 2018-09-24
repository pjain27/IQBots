package api.common;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static api.common.GlobalVariables.AA_BaseURI;
import static api.common.GlobalVariables.AA_PORT;
import static io.restassured.RestAssured.given;

public class CommonMethods {
    public String getToken() throws URISyntaxException {
        String token = "";

        //Full url
        String url = AA_BaseURI + ":" + AA_PORT + "/authentication";

        //Creating URI object, which will be required as an input for request call
        URI uri = new URI(url);

        //Headers object for adding all the headers
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        //Json object for adding request parameters
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "services");
        requestParams.put("password", "12345678");

        //Making a request and getting response output into response object for further validations
        Response response = given()
                .headers(headers)
                .body(requestParams.toString())
                .when()
                .log()
                .all()
                .post(uri);

        if(Integer.toString(response.getStatusCode()).equalsIgnoreCase("200")) {
            //Getting response body from response object
            String responseBody = response.thenReturn().body().asString();
            //To validate response body, we are putting everything into JsonPath object
            JsonPath json = new JsonPath(responseBody);
            token = json.getString("data.token");
        } else {
            System.out.println("Status code: " + Integer.toString(response.getStatusCode()));
        }

        return token;
    }
}
