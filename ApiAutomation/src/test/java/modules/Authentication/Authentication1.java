package api.modules.Authentication;

import api.common.TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static api.common.GlobalVariables.*;
import static api.common.GlobalVariables.totalTestsFailed;
import static api.common.GlobalVariables.totalTestsPassed;
import static io.restassured.RestAssured.given;

public class Authentication1 extends TestBase {
    @BeforeClass
    public void setUpBeforeClass() {
        TEST_DATA_SHEET = "Authentication1";
    }

    @Test(dataProvider = "getTestDataFromExcel")
    public void test(Hashtable<String, String> data) throws URISyntaxException, IOException {

        //We want to run our tests only for the specific testing type
        ExtentTest logMain = null;
        if(isTestDataExecutable(data, logTestForTestBase)) {

            //Increment total tests for reporting
//            totalTestsRan++;
//
//            //Initialization for report
//            logMain = createTestForExtentReport(report, data.get("TestCaseId") + ": " + data.get("Purpose"));
//            logMain.assignCategory(data.get("TestingType"), data.get("Scenario"), TEST_DATA_SHEET);
//
//            //Full url
//            String url = AA_BaseURI + ":" + AA_PORT + data.get("EndPoint");
//            //Adding url into report
//            logInfo(logMain, "<b>URL: </b>" + url);
//
//            //Creating URI object, which will be required as an input for request call
//            URI uri = new URI(url);

            //Headers object for adding all the headers
            Map<String, String> headers = new HashMap<>();
            //Find all headers from excel and attach to the request
            String array1[] = data.get("Header").replace("\n", "").replace("\r", "").split(",");
            for(String s:array1) {
                System.out.println(s);
                String array11[] = s.split("=");
                for(String s1: array11) {
                    System.out.println(s1);
                }
            }

            String array2[] = data.get("BodyParam").replace("\n", "").replace("\r", "").split(",");
            for(String s:array2) {
                System.out.println(s);
                String array11[] = s.split("=");
                for(String s1: array11) {
                    System.out.println(s1);
                }
            }

            /*
            for (Map.Entry m : data.entrySet()) {
                if (m.getKey().toString().contains("Header")) {
                    logInfo(logMain, String.format("<b>Header: </b>%s<br/><b>Value: </b>%s", m.getKey().toString().substring(7), m.getValue().toString()));
                    headers.put(m.getKey().toString().substring(7), m.getValue().toString());
                }
            }

            //Json object for adding request parameters
            JSONObject requestParams = new JSONObject();
            //Find all params from excel and attach to the request
            for (Map.Entry m : data.entrySet()) {
                if (m.getKey().toString().contains("Param")) {
                    logInfo(logMain, String.format("<b>Param: </b>%s<br/><b>Value: </b>%s", m.getKey().toString().substring(6), m.getValue().toString()));
                    requestParams.put(m.getKey().toString().substring(6), m.getValue().toString());
                }
            }

            //Making a request and getting response output into response object for further validations
            Response response = given()
                    .headers(headers)
                    .body(requestParams.toString())
                    .when()
                    .log()
                    .all()
                    .post(uri);

            //Validate status code
            validateExpectedAndActualResults(logMain, "Response Code", data.get("Validate_ResponseCode"), Integer.toString(response.getStatusCode()));

            //Getting response body from response object
            String responseBody = response.thenReturn().body().asString();

            logInfo(logMain, "<b>Response body: </b><br/>" + responseBody);

            //To validate response body, we are putting everything into JsonPath object
            JsonPath json = new JsonPath(responseBody);

            //Validate all responses in response body from excel
            for (Map.Entry m : data.entrySet()) {
                if (m.getKey().toString().contains("Validate_Response_")) {
                    validateExpectedAndActualResults(logMain, "Response for " + m.getKey().toString().substring(18), m.getValue().toString(), json.getString(m.getKey().toString().substring(18)));
                }
            }
            if(logMain.getStatus().equals(Status.PASS)) {
                totalTestsPassed++;
            }
            if(logMain.getStatus().equals(Status.FAIL)) {
                totalTestsFailed++;
            }*/
        }

    }

    @DataProvider
    public Object[][] getTestDataFromExcel() throws IOException {
        return getData2(excelReader);
    }

}
