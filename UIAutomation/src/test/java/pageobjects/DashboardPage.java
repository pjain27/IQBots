package pageobjects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.testBase;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends testBase {

    // Dashboard Link
    @FindBy(how = How.XPATH, using = "//div[@class='breadcrumbs']//a[@href='/dashboard']")
    private WebElement dashboardLink;

    //--- Vandana
    @FindBy(how = How.CSS, using = ".aa-project-report-totals-comparison--metric>span")
    private WebElement totalFileProcessed;
    @FindBy(how = How.CSS, using = ".aa-project-stats-card-progress-metrics--metric>span")
    private WebElement fileProcessedForEachDomain;
    @FindBy(how = How.CSS, using = ".aa-project-stats-card")
    private List<WebElement> allDetailsOfParticularInstance;
    @FindBy(how = How.XPATH, using = "//SPAN[@class='navigation-primaryoption-button-title'][text()='Dashboards']")
    private WebElement dashboardTab;
    @FindBy(how = How.CSS, using = ".aa-project-stats-card-header")
    private List<WebElement> instanceNameHeaderWithStatus;
    private By instanceNameHeaderWithStatusForwaitMethod = By.xpath("//span[@class='icon aa-icon aa-icon-toolbar-user']");
    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/main/div/div[2]/section[1]/div/div[3]/div/div[2]/div[1]")
    private WebElement stpValueFromDashboardPageForAllInstance;

    List<String> actualResult = new ArrayList<String>();

    public DashboardPage() {
    }
    //----

    // Get Dashboard Link
    public WebElement getDashboardLink() {
        return dashboardLink;
    }

    //-- vandana
    // Get Total Files Processed value
    public String getTotalFilesProcessed() {
        // return Integer.parseInt(totalFileProcessed.getText());
        return totalFileProcessed.getText();
    }


    public List<String> getListFP() {
        List<String> fileProcessed = new ArrayList<>();
        List<WebElement> we = Driver.webDriver.findElements(By.cssSelector(".aa-project-stats-card-progress-metrics--metric>span"));
        for (WebElement w : we) {
            fileProcessed.add(w.getText());
        }
        return fileProcessed;
    }


    public void clickOnDashboardTab() {
        dashboardTab.click();
    }

    // Get details from all production instances
    public List<String> getAllInstanceDetail() {
        List<String> instanceDetail = new ArrayList<>();
        List<String> instanceDetailWhichAreOnProduction = new ArrayList<>();
        for (WebElement anAllDetailsOfParticularInstance : allDetailsOfParticularInstance) {
            instanceDetail.add(anAllDetailsOfParticularInstance.getText().replaceAll("[\\r\\n" +
                    "]", ""));
        }
        for (String s : instanceDetail)
            if (s.contains("production"))
                instanceDetailWhichAreOnProduction.add(s);

        return instanceDetailWhichAreOnProduction;

    }

    // Get instance header
    List<WebElement> instanceNameHeaderWithStatus() {
        return instanceNameHeaderWithStatus;
    }

    // Get STP value
    public String getSTPValuefromDashboardPageForAllInstance() {
        return stpValueFromDashboardPageForAllInstance.getText();
    }


    public By getHeaderOfInstance() {
        return instanceNameHeaderWithStatusForwaitMethod;
    }


    public List<String> getStagingInstanceValuesFromDashBoardPage() {
        List<String> stagingInstanceDetails = new ArrayList<>();
        String pathPart1 = "//div[contains(@class, 'aa-project-stats-card') and contains(.,'100%";
        String pathPart2 = Driver.instName;
        String pathLastPart = "stagingThis instance is created using automation script.Domain: InvoicesFiles Uploaded: 1training')]";
        List<WebElement> getStagingValues = Driver.webDriver.findElements(By.xpath(pathPart1+pathPart2+pathLastPart));


        for (WebElement anAllDetailsOfParticularInstance : getStagingValues) {
            stagingInstanceDetails.add(anAllDetailsOfParticularInstance.getText().replaceAll("[\\n" +
                    "]", ""));
        }

        return stagingInstanceDetails;

    }
}
