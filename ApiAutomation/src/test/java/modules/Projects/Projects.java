package api.modules.Projects;

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
import static io.restassured.RestAssured.given;

public class Projects extends TestBase {
    private ExtentTest logMain = null;

    @BeforeClass
    public void setUpBeforeClass() {
        TEST_DATA_SHEET = "Projects";
    }

    @Test(dataProvider = "getTestDataFromExcel")
    public void test(Hashtable<String, String> data) throws URISyntaxException, IOException {

        //We want to run our tests only for the specific testing type
        if(isTestDataExecutable(data, logTestForTestBase)) {
            //Increment total tests for reporting
            totalTestsRan++;
            //Initialization for report
            logMain = createTestForExtentReport(report, data.get("TestCaseId") + ": " + data.get("Purpose"));
            logMain.assignCategory(data.get("TestingType"), data.get("Scenario"), TEST_DATA_SHEET);

            //Full url
            String url = AA_BaseURI + ":" + AA_PORT + data.get("EndPoint");
            //Adding url into report
            logInfo(logMain, "<b>URL: </b>" + url);

            //Creating URI object, which will be required as an input for request call
            URI uri = new URI(url);

            //Headers object for adding all the headers
            Map<String, String> headers = new HashMap<String, String>();
            //Find all headers from excel and attach to the request
            for (Map.Entry m : data.entrySet()) {
                if (m.getKey().toString().contains("Header")) {
                    logInfo(logMain, "<b>Header: </b>" + m.getKey().toString().substring(7) + "<br/><b>Value: </b>" + m.getValue().toString());
                    headers.put(m.getKey().toString().substring(7), m.getValue().toString());
                }
            }
            //Add header for token
            token = getToken();
            headers.put("Authorization", "Bearer " + token);

            //Json object for adding request parameters
            JSONObject requestParams = new JSONObject();
            //Find all params from excel and attach to the request
            for (Map.Entry m : data.entrySet()) {
                if (m.getKey().toString().contains("Param")) {
                    logInfo(logMain, "<b>Param: </b>" + m.getKey().toString().substring(6) + "<br/><b>Value: </b>" + m.getValue().toString());
                    requestParams.put(m.getKey().toString().substring(6), m.getValue().toString());
                }
            }

            Response response = null;
            //Making a request and getting response output into response object for further validations
            switch (data.get("Method").toUpperCase()) {
                case "GET":
                    response = given()
                            .headers(headers)
                            .body(requestParams.toString())
                            .when()
                            .log()
                            .all()
                            .get(uri);
                    break;
                case "POST":
                    response = given()
                            .headers(headers)
                            .body(requestParams.toString())
                            .when()
                            .log()
                            .all()
                            .post(uri);
                    break;
            }

            //Validate status code
            validateExpectedAndActualResults(logMain, "Response Code", data.get("Validate_ResponseCode"), Integer.toString(response.getStatusCode()));

            //Getting response body from response object
            String responseBody = response.thenReturn().body().asString();

            logInfo(logMain, "<b>Response body: </b><br/>" + responseBody);

            //To validate response body, we are putting everything into JsonPath object
            JsonPath json = new JsonPath(responseBody);

            //Validate all responses in response body from excel
            for (Map.Entry m : data.entrySet()) {
                if(m.getKey().toString().contains("Validate_Response_Full")) {
                    validateExpectedAndActualResults(logMain, "Response for Body", m.getValue().toString(), responseBody);
                } else if (m.getKey().toString().contains("Validate_Response_")) {
                    validateExpectedAndActualResults(logMain, "Response for " + m.getKey().toString().substring(18), m.getValue().toString(), json.getString(m.getKey().toString().substring(18)));
                }
            }
            if(logMain.getStatus().equals(Status.PASS)) {
                totalTestsPassed++;
            }
            if(logMain.getStatus().equals(Status.FAIL)) {
                totalTestsFailed++;
            }
        }

        logMain = null;
    }

    @DataProvider
    public Object[][] getTestDataFromExcel() throws IOException {
        return getData2(excelReader);
    }
}
