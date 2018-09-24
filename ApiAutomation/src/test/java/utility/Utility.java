package api.utility;

import api.common.CommonMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import static api.common.GlobalVariables.*;

public class Utility extends CommonMethods{

    public static ExtentReports report = null;
    public static ExtentXReporter extentXReporter = null;
    protected static ExtentHtmlReporter htmlReporter = null;

    //Initiate variable for log4j
    protected static Log log4j;
    protected boolean log4jStatus = false;
    protected static ExcelReader excelReader = null;
    public static ExcelReader excelReader1 = null;
    //Initiate local variables for generating time stamp
    private static MyStringRandomGen myStringRandomGen = new MyStringRandomGen();
    private static String timeStampString = myStringRandomGen.generateTimeStampString();

    //Initiate local variables for sending email
    protected static String reportLocation = System.getProperty("user.dir") + REPORT_PATH + "report-" + timeStampString + "/";
    protected static String reportFilePath = reportLocation + "Report-" + timeStampString + ".html";
    //public static EmailActions emailActions = new EmailActions();

    protected void log4jConfiguration() {
        try {
            log4j = LogFactory.getLog(getClass());
            log4jStatus = true;
        } catch (Exception e) {
            System.out.println("TestBase: log4j initialization fails. Stack Trace is below: <br/>" + e);
        }
    }



    /**
     * @param logTest Extent Test
     * @param expected Expected
     * @param actual Actual
     * @Action name: validateExpectedAndActualResults
     */
    protected void validateExpectedAndActualResults(ExtentTest logTest, String whatAreWeValidating, String expected, String actual) {
        try {
            if (actual.trim().equalsIgnoreCase(expected)) {
                logPass(logTest, "<b>Validating " + whatAreWeValidating + "</b><br/>", "<font color='green'>Expected Result: " + expected + "<br/>Actual Result: " + actual + "</font>");
            } else {
                logFail(logTest, "<b>Validating " + whatAreWeValidating + "</b><br/>", "<font color='red'>Expected Result: " + expected + "<br/>Actual Result: " + actual + "</font>");
            }
        } catch (Exception e) {
            log4j.error("validateExpectedAndActualResults. <br/> Expected: " + expected + "<br/>Actual: "+ actual + "<br/>Exception: " + e);
            logException(logTest,"validateExpectedAndActualResults method. <br/><b>Validating " + whatAreWeValidating + "</b><br/><font color='red'>Expected Result: " + expected + "<br/>Actual Result: " + actual + "<br/>Exception: ", e);
        }
    }


    protected static void logInfo(ExtentTest logTest, String description) {
        logTest.info(description);
    }

    private static void logSkip(ExtentTest logTest, String description) {
        if (SHOW_SKIP) logTest.skip(MarkupHelper.createLabel(description, ExtentColor.GREY));
    }

    protected static void logFail(ExtentTest logTest, String description, String validateDescription) {
        logTest.fail(description + validateDescription);
       /* if (zephyrUpdate) {
            markTestCaseFailInZephyr(TESTCASEID, description + "\n\n" + descZephyr, logTest);
        }
        */
    }

    private static void logPass(ExtentTest logTest, String description, String validateDescription) {
        logTest.pass(description + validateDescription);
        //logTest.pass(MarkupHelper.createLabel(validateDescription, ExtentColor.GREEN));
    }

    protected static void logException(ExtentTest logTest, String description, Exception exception) {
        logFail(logTest, description,  exception + "<br/>" + exception.getMessage() + "</font>");
    }

    public static ExtentTest logStepInfo(ExtentTest logTest, String description) {
        return logTest.createNode(description);
    }

    protected ExtentTest createTestForExtentReport(ExtentReports report, String description) {
        return report.createTest(description);
    }

    public static ExtentTest createNodeForExtentReport(ExtentTest log, String description) {
        return log.createNode(description);
    }


    public static void sleep(ExtentTest logTest, long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (Exception e) {
            log4j.error("Exception is sleep method - ERROR - " + e);
            logException(logTest, "Exception is sleep method", e);
        }
    }





    // ******** Reading from Excel starts here ****************//


    /**
     * Reading from Excel file for which test data to be executed methods
     *
     * @param data - test data row
     * @return true if test data has to be executed along with Runmode,
     * otherwise return false.
     * @author quoc.le
     */

    protected static boolean isTestDataExecutable(Hashtable<String, String> data, ExtentTest logTest) {
        boolean testDataRun = false;
        boolean testingType = false;

        try {
            // if Testing Type = Smoke, execute Smoke test cases
            if (TESTING_TYPE.equalsIgnoreCase("Smoke") && data.get("TestingType").equalsIgnoreCase("Smoke")) {
                testingType = true;
            }
            // if Testing Type = Sanity, execute Sanity + Smoke test cases
            else if (TESTING_TYPE.equalsIgnoreCase("Sanity") && ((data.get("TestingType").equalsIgnoreCase("Sanity") || data.get("TestingType").equalsIgnoreCase("Smoke")))) {
                testingType = true;
            }
            // if Testing Type = Regression, execute Sanity + Smoke + Regression test cases
            else if (TESTING_TYPE.equalsIgnoreCase("Regression") && ((data.get("TestingType").equalsIgnoreCase("Sanity") || data.get("TestingType").equalsIgnoreCase("Smoke") || data.get("TestingType").equalsIgnoreCase("Regression")))) {
                testingType = true;
            }

            if (data.get("RunMode").equalsIgnoreCase("Y") && testingType)
                testDataRun = true;
            else {
                if (!data.get("RunMode").equalsIgnoreCase("Y") && testingType)
                    logSkip(logTest, "Skipping test as it's set to 'N' for '" + data.get("No.") + "' test data");
                else
                    logSkip(logTest, "Skipping test as '" + data.get("TestingType") + "' testing type is NOT matched for '" + data.get("No.") + "' test data");
            }

        } catch (Exception e) {
            log4j.error("isTestDataExecutable method. Error" + e);
            logException(logTest, "isTestDataExecutable method<br/>", e);
        }
        return testDataRun;
    }

    // ******** Reading from Excel ends here ****************//

    /**
     * Reading test data from the excel sheet for a specific test case
     *
     * @param testName Name of the test
     * @param excelSheet Excel sheet name
     * @param excelReader Excel Reader
     * @return Test Data Object in Key Value pair.
     * <p>
     * Operations in Sequence: Find row number from where the test1
     * starts Find number of columns in the test Find number of rows in
     * the test Put the data into Hashtable Put Hashtable into an array
     * Return array
     * The action name is the same as other action. But it use override method when running.
     */
    private static Object[][] getData(String testName, String excelSheet, ExcelReader excelReader) throws IOException {

        int testStartRowNum = 0;
        // Find the row number from where test starts
        for (int rowNumber = 1; rowNumber <= excelReader.getRowCount(excelSheet); rowNumber++) {
            if (excelReader.getCellData(excelSheet, 0, rowNumber).equals(testName)) {
                testStartRowNum = rowNumber;
                break;
            }
        }

        // Column will start in the next row of test case
        int colStartRowNum = testStartRowNum + 1;
        int totalCols = 0;
        // Find all the columns till we get empty ("")
        while (!excelReader.getCellData(excelSheet, totalCols, colStartRowNum).equals("")) {
            totalCols++;
        }

        // Find out how many rows of data we have?
        // Data starts from 2nd row from the test1
        int dataStartRowNum = testStartRowNum + 2;
        int totalRows = 0;
        // Find all the rows till we get empty ("")
        while (!excelReader.getCellData(excelSheet, 0, dataStartRowNum + totalRows).equals("")) {
            totalRows++;
        }

        // extract all the data
        Object[][] data = new Object[totalRows][1];
        int index = 0;
        Hashtable<String, String> table;
        // Navigate through all the data rows one by one and add the data into
        // Hashtable.
        for (int rowNumber = dataStartRowNum; rowNumber < (dataStartRowNum + totalRows); rowNumber++) {
            table = new Hashtable<>();
            // Get all the cells data from each row
            for (int columnNumber = 0; columnNumber < totalCols; columnNumber++) {
                table.put(excelReader.getCellData(excelSheet, columnNumber, colStartRowNum),
                        excelReader.getCellData(excelSheet, columnNumber, rowNumber));
            }
            data[index][0] = table;
            index++;
        }
        return data;
    }


    public static Object[][] getData(String testName, ExcelReader excelReader) throws IOException {
        return getData(testName, TEST_DATA_SHEET, excelReader);
    }


    public static Object[][] getData1(ExcelReader excelReader) throws IOException {

        int testStartRowNum = rowNumberInExcel;
        rowNumberInExcel +=2;

        int totalCols = 0;
        // Find all the columns till we get empty ("")
        while (!excelReader.getCellData(TEST_DATA_SHEET, totalCols, testStartRowNum).equals("")) {
            totalCols++;
        }


        // extract all the data
        Object[][] data = new Object[1][1];
        Hashtable<String, String> table = new Hashtable<String, String>();
        for (int columnNumber = 0; columnNumber < totalCols; columnNumber++) {
            table.put(excelReader.getCellData(TEST_DATA_SHEET, columnNumber, testStartRowNum),
                    excelReader.getCellData(TEST_DATA_SHEET, columnNumber, testStartRowNum+1));
        }
        data[0][0] = table;
        return data;
    }

    protected static Object[][] getData2(ExcelReader excelReader) throws IOException {

        int testStartRowNum = 1;

        int howManyTests = excelReader.getRowCount(TEST_DATA_SHEET)/2;

        Object[][] data = new Object[howManyTests][1];

        for(int test=0; test < howManyTests; test++) {
            //find out how many columns for each test
            int totalCols = 0;
            // Find all the columns till we get empty ("")
            while (!excelReader.getCellData(TEST_DATA_SHEET, totalCols, testStartRowNum).equals("")) {
                totalCols++;
            }

            Hashtable<String, String> table = new Hashtable<>();

            for (int columnNumber = 0; columnNumber < totalCols; columnNumber++) {
                table.put(excelReader.getCellData(TEST_DATA_SHEET, columnNumber, testStartRowNum),
                        excelReader.getCellData(TEST_DATA_SHEET, columnNumber, testStartRowNum+1));
            }

            data[test][0] = table;

            testStartRowNum+=2;

        }

        return data;
    }

    protected static void inputReportData(ExcelReader excelReader, int totalTestsRan, int totalTestsPassed, int totalTestsFailed) throws IOException {
        String sheetName = "Results";
        int numberOfRows = excelReader.getRowCount(sheetName);

        //Add DateTime
        excelReader.setCellData(sheetName, "DateTime", numberOfRows+1, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));

        //Add TestsRan
        excelReader.setCellData(sheetName, "TestsRan", numberOfRows+1, Integer.toString(totalTestsRan));

        //Add TestsPassed
        excelReader.setCellData(sheetName, "TestsPassed", numberOfRows+1, Integer.toString(totalTestsPassed));

        //Add TestsFailed
        excelReader.setCellData(sheetName, "TestsFailed", numberOfRows+1, Integer.toString(totalTestsFailed));

    }
    //For Zephyr - PASS
    public static void markTestCasePassInZephyr(String testCaseId, String description, ExtentTest logTest) throws IOException {
        if (zephyrUpdate) {
            try {
                logInfo(logTest, "Marking TestCaseId: " + testCaseId + " as PASS");
            } catch (Exception e) {
                logException(logTest, "markTestCasePassInZephyr failed updating Zephyr Error: ", e);
            }
        }
    }

    //For Zephyr - FAIL
    public static void markTestCaseFailInZephyr(String testCaseId, String description, ExtentTest logTest) throws IOException {
        if (zephyrUpdate) {
            try {
                logInfo(logTest, "Marking TestCaseId: " + testCaseId + " as FAIL");
            } catch (Exception e) {
                logException(logTest, "markTestCaseFailInZephyr failed updating Zephyr Error: ", e);
            }
        }
    }
}