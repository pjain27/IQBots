package implementation;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;
import utils.testBase;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import static com.thoughtworks.gauge.Gauge.writeMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardPageStepImplementation {

    private PerformanceReportPage performanceReportPage = PageFactory.initElements(Driver.webDriver, PerformanceReportPage.class);
    private DashboardPage dashBoardPage = PageFactory.initElements(Driver.webDriver, DashboardPage.class);
    private long sum = 0, totalProcessed;
    private List<String> filteredListWithFPAndSTPandAccuracyValuesFromDashboard = new ArrayList<>();
    private String getSTPValueForAllInstanceFromDashboardPage;
    private testBase baseClass = PageFactory.initElements(Driver.webDriver, testBase.class);

    @Step("Then dashboard should be displayed for service user")
    public void waitForDashboard() throws InterruptedException {
        // Given

        // When

        // Then
        assertThat(dashBoardPage.getDashboardLink().getText()).isEqualTo("Dashboard");
        assertThat(Driver.webDriver.getTitle()).contains("Automation Anywhere | Dashboards");

        writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Get the total number of files processed on dashboard page")
    public void getTotalNumberOfFilesProcessed() {
        // Given
        totalProcessed = Long.valueOf(dashBoardPage.getTotalFilesProcessed().replaceAll(",", ""));
        System.out.println("Total Files Processed" + totalProcessed);

        // When

        // Then

    }

    @Step("Get all the file processed from each domain")
    public void getallFileProcessedFromEachDomain() {
        // Given

        // When
        List<String> allFP = dashBoardPage.getListFP();
        for (String number : allFP) {
            number = number.replaceAll(",", "");
            sum += Integer.parseInt(number);
        }
        System.out.println("sum of all elements=" + sum);
    }


    @Step("Check total number of files Processed should be same as sum of file processed in particular domain which are in production")
    public void totalNumberOfFilesProcessedIsEqualToSumOfFPInParticulardomainWhichAreInProduction() {
        // Given

        // When

        // Then
        assertThat(totalProcessed).isEqualTo(sum);
    }

    @Step("Click on Dashboard tab")
    public void clickOnDashBoardTab() {
        // Given

        // When
        dashBoardPage.clickOnDashboardTab();

    }

    public List<String> displayValueOfAllInstance(int i) {
        // Given

        // When
        List<String> dashBoardInstanceDetailList = dashBoardPage.getAllInstanceDetail();
        filteredListWithFPAndSTPandAccuracyValuesFromDashboard.add(dashBoardInstanceDetailList.get(i).substring(dashBoardInstanceDetailList.get(i).indexOf("production") + 10));
        return filteredListWithFPAndSTPandAccuracyValuesFromDashboard;
    }

    @Step("Get the STP value from Dashboard page")
    public void getSTPValueFromDashboardpage() {
        // Given

        // When
        getSTPValueForAllInstanceFromDashboardPage = dashBoardPage.getSTPValuefromDashboardPageForAllInstance();
    }

    private static final int FILES_UPLOADED = 0, FILES_UPLOADED_IN_PROCESSINGrESULTS = 1, FILES_SUCCESSFULLY_PROCESSED = 2;

    @Step("Click on each instance which are in production and get sum of file successfully Processed and sum of files uploaded and calculate STP")
    public void getSumOfFilesSPAndFilesUploadedByClickingOnEachInstanceWhichAreInProduction() throws InterruptedException {
        // Given
        List<String> instancedetailsDashboardPage = new ArrayList<>();
        List<String> dashboardPageInstanceDetails = new ArrayList<>();
        List<String> instanceTotalList = new ArrayList<>();
        float sumOffileSuccesssfullyPocessed = 0, sumOfFilesUploaded = 0;
        int i;

        // When
        // Get the list of instance which are in production
        List<String> listOfInstanceOnProduction = performanceReportPage.getDashBoardInstanceName();
        System.out.println(listOfInstanceOnProduction);
        // if we have instance which are in production
        if (listOfInstanceOnProduction.size() > 0) {
            for (i = 0; i < listOfInstanceOnProduction.size(); i++) {
                baseClass.waitForElementClick(dashBoardPage.getHeaderOfInstance());
                // Step-3 Click on instance from list
                performanceReportPage.clickOnEachInstance(i);

                // Then
                assertThat(performanceReportPage.getPerformanceReportpageTitle().getText()).isEqualTo("Performance Report");

                // Given

                // When
                // Get the list of file processed
                List<String> getAllValuesFormPerformanceReportPage = performanceReportPage.getPerformanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResults();
                sumOffileSuccesssfullyPocessed += Integer.parseInt(getAllValuesFormPerformanceReportPage.get(FILES_SUCCESSFULLY_PROCESSED).replaceAll(",", "").trim());
                sumOfFilesUploaded = sumOfFilesUploaded + Integer.parseInt(getAllValuesFormPerformanceReportPage.get(FILES_UPLOADED).replaceAll(",", "").trim());
                dashBoardPage.clickOnDashboardTab();
                Driver.webDriver.navigate().refresh();
            }
            float calculateSTP = (sumOffileSuccesssfullyPocessed) / sumOfFilesUploaded;
            System.out.println("Files Successfully Processed =" + sumOffileSuccesssfullyPocessed);
            System.out.println("Files Uploaded =" + sumOfFilesUploaded);
            NumberFormat defaultFormat = NumberFormat.getPercentInstance();
            defaultFormat.setMinimumFractionDigits(0);
            String STP;
            if (sumOffileSuccesssfullyPocessed == 0.0 && sumOfFilesUploaded == 0.0) {
                STP = "0%";
            } else {
                STP = defaultFormat.format(calculateSTP);
            }
            System.out.println("STP = " + defaultFormat.format(calculateSTP));

            // Then
            assertThat(getSTPValueForAllInstanceFromDashboardPage).isEqualTo(STP);
        }
    }

    @Step("Validate values of learning instance when instance is in staging")
    public void learningInstanceDetailOnStaging() {
        // Given
        List<String> expectedListStagingvalues  = new ArrayList<>();

        // When
         expectedListStagingvalues.add("100%"+Driver.instName+"stagingThis instance is created using automation script.Domain: InvoicesFiles Uploaded: 1training");

        // Then
         assertThat(dashBoardPage.getStagingInstanceValuesFromDashBoardPage()).isEqualTo(expectedListStagingvalues);


    }

}
