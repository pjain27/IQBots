package api.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import api.utility.ExcelReader;
import api.utility.Utility;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static api.common.GlobalVariables.*;
import static api.utility.EmailActions.sendEmailReport;

public class TestBase extends Utility {

    //Initiate local variables for extent report
    protected ExtentTest logTestForTestBase = null;
    public static boolean excelReaderObjectInitialized = false;
    public static boolean extentReportInitialized = false;
    public static boolean testingTypeInitialized = false;
    public static boolean everythingInitializedCorrectly = false;


    //Initiate local variable for excel file
    @BeforeSuite(alwaysRun = true)
    public void setUpBeforeSuite() throws IOException, URISyntaxException {
        File folder = new File(getOutputFolder());
        folder.mkdirs();
        //Initiate log4j property system
        log4jConfiguration();
        DOMConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\input\\log4j.xml");

        if(System.getProperty("reportEmailAddressFromJenkins")!=null)
            to = System.getProperty("reportEmailAddressFromJenkins");

        if(System.getProperty("zephyrUpdateFromJenkins")!=null)
            if(System.getProperty("zephyrUpdateFromJenkins").equalsIgnoreCase("true"))
                zephyrUpdate=true;

        if (log4jStatus) {
            try {
                report = new ExtentReports();
                htmlReporter = new ExtentHtmlReporter(reportFilePath);
                htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\input\\config.xml"));
                report.attachReporter(htmlReporter);
                report.setSystemInfo("IQBotVersion", "5.2.1");
                logTestForTestBase = createTestForExtentReport(report, "Initial Setup");
                extentReportInitialized = true;
            } catch (Exception e) {
                log4j.error("Extent report initialization error in TestBase. Error: " + e);
                logException(logTestForTestBase, "Extent report initialization error in TestBase. Error: ", e);
            }

            if (extentReportInitialized) {
                // Initializing Excel object
                try {
                    excelReader = new ExcelReader(System.getProperty("user.dir") + GlobalVariables.EXCEL_PATH, logTestForTestBase);
                    excelReaderObjectInitialized = true;
                    logInfo(logTestForTestBase, "Excel sheet initialization done");
                } catch (Exception e) {
                    log4j.error("Excel Sheet Initialization failed in TestBase. Error: " + e);
                    logException(logTestForTestBase, "Excel Sheet Initialization failed in TestBase. Error: ", e);
                }

                if (excelReaderObjectInitialized) {
                    // Setting URL
                    try {
                        AA_BaseURI = excelReader.getCellData(CONFIGURATION_SHEET, "URL", 2);
                        if(System.getProperty("urlFromJenkins")!=null)
                            AA_BaseURI = System.getProperty("urlFromJenkins");
                        logInfo(logTestForTestBase, "URL: " + AA_BaseURI);
                    } catch (Exception e) {
                        log4j.error("Excel Sheet Configuration: Could not find out URL or Port Or TestingType. Error: " + e);
                        logException(logTestForTestBase, "Excel Sheet Configuration: Could not find out URL or Port Or TestingType. Error: ", e);
                    }
                    // Setting Port
                    try {
                        AA_PORT = excelReader.getCellData(CONFIGURATION_SHEET, "Port", 2);
                        if(System.getProperty("portFromJenkins")!=null)
                            AA_PORT = System.getProperty("portFromJenkins");
                        logInfo(logTestForTestBase, "Port: " + AA_PORT);
                    } catch (Exception e) {
                        log4j.error("Excel Sheet Configuration: Could not find out URL or Port Or TestingType. Error: " + e);
                        logException(logTestForTestBase, "Excel Sheet Configuration: Could not find out URL or Port Or TestingType. Error: ", e);
                    }
                    //Setting Testing Type
                    try {
                        TESTING_TYPE = excelReader.getCellData(CONFIGURATION_SHEET, "TestingType", 2);
                        if(System.getProperty("testingTypeFromJenkins")!=null)
                            TESTING_TYPE = System.getProperty("testingTypeFromJenkins");
                        logInfo(logTestForTestBase, "Testing Type: " + TESTING_TYPE);
                    } catch (Exception e) {
                        log4j.error("Excel Sheet Configuration: Could not find out URL or Port Or TestingType. Error: " + e);
                        logException(logTestForTestBase, "Excel Sheet Configuration: Could not find out URL or Port Or TestingType. Error: ", e);
                    }
                    if (!TESTING_TYPE.equalsIgnoreCase("")) {
                        if (TESTING_TYPE.equalsIgnoreCase("Regression") || TESTING_TYPE.equalsIgnoreCase("Smoke") || TESTING_TYPE.equalsIgnoreCase("Sanity")) {
                            testingTypeInitialized = true;
                        } else {
                            logFail(logTestForTestBase, "TESTING_TYPE name in Excel seems wrong: ", TESTING_TYPE);
                        }
                    }
                }
            }
        }

        if (log4jStatus && extentReportInitialized && excelReaderObjectInitialized && testingTypeInitialized)
            everythingInitializedCorrectly = true;

        //Authentication Token
        //token = getToken();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAfterSuite() throws Exception {
        if (log4jStatus && extentReportInitialized) {
            inputReportData(excelReader, totalTestsRan, totalTestsPassed, totalTestsFailed);
            excelReader = null;
            List statusHierarchy = Arrays.asList(
                    Status.FATAL,
                    Status.FAIL,
                    Status.ERROR,
                    Status.WARNING,
                    Status.PASS,
                    Status.SKIP,
                    Status.INFO,
                    Status.DEBUG
            );
            report.config().statusConfigurator().setStatusHierarchy(statusHierarchy);

            report.flush();
            report = null;
            //sendEmailReport(reportFilePath, logTestForTestBase);
            logTestForTestBase = null;
            log4j = null;

            if(zephyrUpdate) {
                //Create test cycle with specific criteria
                //Update Zephyr for all test cases
            }


        }
    }

    protected String getOutputFolder() {
        return reportLocation;
    }
}
