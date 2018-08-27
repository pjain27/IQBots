package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import utils.testBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class PerformanceReportPageStepImplementation {

    private PerformanceReportPage performancePage = PageFactory.initElements(Driver.webDriver, PerformanceReportPage.class);
    private DashboardPage dashPage = PageFactory.initElements(Driver.webDriver, DashboardPage.class);
    private DashboardPageStepImplementation dashboardstepImplementation = PageFactory.initElements(Driver.webDriver, DashboardPageStepImplementation.class);

    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);
    private List<String> actualResult = new ArrayList<>();
    private List<String> listOfInstanceOnProduction = new ArrayList<>();
    List<String> filteredListWithFPAndSTPandAccuracyValuesFromDashboard = new ArrayList<>();


    @Step("Get all the instance name which are in production")
    public void clickOnInstanceNameToCheckPerformanceReport() {
        // Given

        // When
        listOfInstanceOnProduction = performancePage.getDashBoardInstanceName();

        System.out.println(listOfInstanceOnProduction);

        // Then

    }

    @Step("Click on instance name")
    public void clcikOnInstanceName() throws InterruptedException {
        // Given
        baseClass.explicitWait("//span[text()='My Learning Instances']");

        // When
        performancePage.clickOnInstance(Driver.instName);

        // Then

    }

    @Step("User will be landed to Performance Report Page")
    public void performanceReportPageTitle() throws InterruptedException {
        // Given

        // When
        //  performancePage.waitForPerformanceReportHeading();
        baseClass.explicitWait("//a[text()='Performance Report']");

        // Then
        assertThat(performancePage.getPerformanceReportpageTitle().getText()).isEqualTo("Performance Report");
        actualResult = performancePage.getPerformanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResults();

    }

    // used to extract data from the returned list when using validateWithExpectedProcessingResult() method.
    private static final int FILES_UPLOADED = 0, FILES_UPLOADED_IN_PROCESSINGRESULTS = 1, FILES_SUCCESSFULLY_PROCESSED = 2;
    private static final int FILES_SENT_TO_VALIDATION = 3, FILES_VALIDATED = 4, FILES_INVALID = 5;

    @Step("Validate Processing result values for <Performance Report Spec>")
    public void validateWithExpectedProcessingResult(String getSpecFile) throws Exception {
        // Given

        //  String expectedFilesProcessed,expectedSTP,expectedAccuracy;
        String expectedFilesUploaded, expectedFilesProcessedInProcesseingResult, expectedFilesSuccessfullyProcessed;
        String expectedFileSentToValidation, expectedFilesValidated, expectedFilesInvalid;
        String expectedResultsSheetName = "PerformanceReportActualResults";

        System.out.println("getSpecFile = " + getSpecFile);
        if (getSpecFile.equals("Performance Report Spec")) {
            expectedFilesUploaded = "0";
            expectedFilesProcessedInProcesseingResult = "0";
            expectedFilesSuccessfullyProcessed = "0";
            expectedFileSentToValidation = "0";
            expectedFilesValidated = "0";
            expectedFilesInvalid = "0";
        } else {
            expectedFilesUploaded = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 1, 1);
            expectedFilesProcessedInProcesseingResult = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 2, 1);
            expectedFilesSuccessfullyProcessed = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 3, 1);
            expectedFileSentToValidation = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 4, 1);
            expectedFilesValidated = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 5, 1);
            expectedFilesInvalid = testBase.getData(System.getenv("TestDataPath"), expectedResultsSheetName, 6, 1);
        }

        // When

        // Then
        //PERFORMANCE : 6PERFORMANCE : 1PERFORMANCE : 0PERFORMANCE : 1PERFORMANCE : 0PERFORMANCE : 0Actual result -[6, 1, 0, 1, 0, 0]
        assertThat(actualResult.get(FILES_UPLOADED)).isEqualTo(expectedFilesUploaded);
        assertThat(actualResult.get(FILES_UPLOADED_IN_PROCESSINGRESULTS)).isEqualTo(expectedFilesProcessedInProcesseingResult);
        assertThat(actualResult.get(FILES_SUCCESSFULLY_PROCESSED)).isEqualTo(expectedFilesSuccessfullyProcessed);
        assertThat(actualResult.get(FILES_SENT_TO_VALIDATION)).isEqualTo(expectedFileSentToValidation);
        assertThat(actualResult.get(FILES_VALIDATED)).isEqualTo(expectedFilesValidated);
        assertThat(actualResult.get(FILES_INVALID)).isEqualTo(expectedFilesInvalid);
    }


    @Step("Click on each instance which are in production to validate STP Accuracy and Total files processed")
    public void clickOnEachInstanceWhichAreInProduction() throws InterruptedException {
        // Given
        List<String> instancedetailsDashboardPage = new ArrayList<>();
        List<String> dashboardPageInstanceDetails = new ArrayList<>();
        List<String> instanceTotalList = new ArrayList<>();
        int i;
        //  baseClass.explicitWait("//div[@class='aa-page-loading-overlay-loader']");
        //  baseClass.explicitWait("//a/header");
        // When
        // Get the list of instance which are in production
        listOfInstanceOnProduction = performancePage.getDashBoardInstanceName();
        System.out.println(listOfInstanceOnProduction);
        if (listOfInstanceOnProduction.size() > 0) {
            for (i = 0; i < listOfInstanceOnProduction.size(); i++) {
                Thread.sleep(5000);
                // Step-2 Get instance detail from Dashboard
                instancedetailsDashboardPage = dashboardstepImplementation.displayValueOfAllInstance(i);
                System.out.println("2" + instancedetailsDashboardPage);
                instancedetailsDashboardPage.set(i, instancedetailsDashboardPage.get(i).replaceAll("[^0-9.,%]+", "").trim());
                System.out.println("Dashboard Page Instance Values" + instancedetailsDashboardPage.get(i));

                dashboardPageInstanceDetails = instancedetailsDashboardPage;
                baseClass.waitForElementClick(dashPage.getHeaderOfInstance());
                // Step-3 Click on instance from list
                performancePage.clickOnEachInstance(i);
                // Step-4 we are on performance page
                assertThat(performancePage.getPerformanceReportpageTitle().getText()).isEqualTo("Performance Report");
                // Step-5 get the instance detail from performance page
                System.out.println("Performance report Page Instance Values" + performancePage.getInstanceTotals());

                final String[] performanceReportPageInstanceDetails = {""};
                performancePage.getInstanceTotals().forEach(text -> {
                    performanceReportPageInstanceDetails[0] = performanceReportPageInstanceDetails[0] + text;
                });
                System.out.println(performanceReportPageInstanceDetails[0]);

                // Then
                assertThat(instancedetailsDashboardPage.get(i)).isEqualTo(performanceReportPageInstanceDetails[0]);

                // Given

                // When
                dashPage.clickOnDashboardTab();
                Driver.webDriver.navigate().refresh();
            }
        } else {
            writeMessage("There are no instance on production");
        }
    }


}
