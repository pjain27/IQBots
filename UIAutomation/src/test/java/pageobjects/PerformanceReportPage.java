package pageobjects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class PerformanceReportPage {

    @FindBy(how = How.XPATH, using = ".//*[@id='app']/div/div/main/div/div[1]/div[3]/a")
    private WebElement performanceReportPageTitle;

    @FindBy(how = How.CSS, using = ".aa-project-report-stat-card--metric>span")
    private List<WebElement> performanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResults;
    @FindBy(how = How.XPATH, using = "//div[@class='aa-project-report-totals-comparison--metric']")
    private List<WebElement> performanceReportFilesProcessedSTPAccuracy;
    @FindBy(how = How.CSS, using = ".aa-project-report-totals-comparison--metric")
    private List<WebElement> instanceTotals;
    private WebDriverWait wait = new WebDriverWait(Driver.webDriver, 30);

    private DashboardPage dashboardPage = PageFactory.initElements(Driver.webDriver, DashboardPage.class);


    private List<String> instanceNameHeaderOfProduction = new ArrayList<String>();

    // Get all instances names which are in production
    public List<String> getDashBoardInstanceName() {
        List<String> instanceNameHeaderList = new ArrayList<>();
        instanceNameHeaderOfProduction = new ArrayList<>();
        for (int i = 0; i < dashboardPage.instanceNameHeaderWithStatus().size(); i++) {
            instanceNameHeaderList.add(dashboardPage.instanceNameHeaderWithStatus().get(i).getText().trim());
        }
        for (String s : instanceNameHeaderList)
            if (s.contains("production"))
                instanceNameHeaderOfProduction.add(s);
        return instanceNameHeaderOfProduction;
    }

    // Perform click action on instance name
    public void clickOnInstance (String instanceName){

            for (int i = 0; i < instanceNameHeaderOfProduction.size(); i++) {

                if (instanceNameHeaderOfProduction.get(i).trim().equalsIgnoreCase(instanceName + "\n" + "production")) {
                    dashboardPage.instanceNameHeaderWithStatus().get(i).click();
                    break;
                }
            }
        }

    // Perform click action on instance name
    public void clickOnEachInstance(int i) {
        dashboardPage.instanceNameHeaderWithStatus().get(i).click();
    }

    // Get performance report title
    public WebElement getPerformanceReportpageTitle() {
        return performanceReportPageTitle;
    }

    // Get Files processed, Accuracy & STP for all production instances
    public List<String> getPerformanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResults() {

        List<String> performanceReportResult = new ArrayList<String>();
        for (WebElement performanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResult : performanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResults) {
            System.out.print("PERFORMANCE : " + performanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResult.getText());
            performanceReportResult.add(performanceReportFilesProcessedSTPAccuracyAndValuesOfProcessingResult.getText());
        }
        return performanceReportResult;
    }

    // Get total instances count
    public List<WebElement> instanceTotals() {
        return instanceTotals;
    }

    // Calculate total instances count
    public List<String> getInstanceTotals() {
        List<String> performanceReportPageInstanceTotalsList = new ArrayList<String>();
        for (WebElement instanceTotal : instanceTotals) {
            performanceReportPageInstanceTotalsList.add(instanceTotal.getText());
        }
        return performanceReportPageInstanceTotalsList;
    }

    // Wait for performance report page heading
    public void waitForPerformanceReportHeading() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='app']/div/div/main/div/div[1]/div[3]/a")));
    }
}
