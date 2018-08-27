package driver;

import com.thoughtworks.gauge.*;

import jiraIntegration.UpdateExecutionStatusToJira;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.testBase;

public class Driver {

    // Holds the WebDriver instance
    public static WebDriver webDriver;
    public static int a=1;
    // Holds Instance Name Throughout The Suite
    public static String instName;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    private LocalDate localDate = LocalDate.now();

    public Driver() {
        webDriver = webDriver;
    }

    static {
        copyResources(new File(System.getenv("source_testDocument_Path")), new File(System.getenv("test_Documents_FolderPath")));

    }

    // Copy Test Documents and resources before test run
    private static void copyResources(File sourcePath, File destinationPath) {
        try {
           org.apache.commons.io.FileUtils.copyDirectory(sourcePath, destinationPath);

        } catch (FileAlreadyExistsException exp) {
            //destination file already exists
        } catch (IOException exp) {
        /* something else went wrong */
            exp.printStackTrace();
        }
    }

    // Create File to store Instance Name
    private void logInstanceNameIntoFile(String instName) throws Exception {
        String fileName = "Instance.txt";
        String path = System.getenv("test_Documents_FolderPath");
        String fullPath = path + "\\" + fileName;
        File file = new File(fullPath);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        Writer w = new BufferedWriter(osw);
        w.write("Instance_Name=" + instName);
        w.close();
    }

    // Initialize a webDriver instance of required browser
    // Since this does not have a significance in the application's business
    // domain, the BeforeSuite hook is used to instantiate the webDriver
    @BeforeSuite
    public void initializeDriver() throws Exception {
        webDriver = DriverFactory.getDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        String currentWindow = webDriver.getWindowHandle();
        webDriver.switchTo().window(currentWindow);
        PageFactory.initElements(webDriver, this);
        System.out.println("Init Elements");
        instName = System.getenv("instance_Name");
        instName = instName + "_" + localDate + "_" + System.currentTimeMillis();
        logInstanceNameIntoFile(instName);
    }

    // Update Status To Jira
   /* @AfterScenario
    public void UpdateTestCaseStatusInJira(ExecutionContext context) throws Exception {

        StepDetails stepName = context.getCurrentStep();
        boolean stepStatus = stepName.getIsFailing();
        Scenario scenarioName = context.getCurrentScenario();
        int methodExecutionStatus;
        if (!stepStatus)
            methodExecutionStatus = 1;
        else
            methodExecutionStatus = 2;
        System.out.println(
                "Scenario Name: " + scenarioName.getName() + " ExecutionStatus: " + methodExecutionStatus);
        UpdateExecutionStatusToJira updateTestCaseExecutionStatusToZephyrForJira = new UpdateExecutionStatusToJira();
        updateTestCaseExecutionStatusToZephyrForJira.update(scenarioName.getName(), methodExecutionStatus);

    } */
/*
   //................................................................................................................
   @AfterStep
   public void scenarioStatus(ExecutionContext context)throws Exception{
       //By Pritisundar
       String stepName=context.getCurrentStep().getText();
       System.out.println("stepName = " + stepName);
       boolean stepStatus=context.getCurrentStep().getIsFailing();
       System.out.println(stepStatus);
       String scenarioName = context.getCurrentScenario().getName();
       //String nameScenario=scenarioName.toString();
       System.out.println("**** [" + scenarioName +"]");
       String testcase = testBase.getData(System.getenv("ReportDataPath"), "ReportFile", a, 0).trim();
       System.out.println(testcase);

       if(!stepStatus) {

          // String testcase = testBase.getData(System.getenv("ReportDataPath"), "ReportFile", a, 0);
          // System.out.println(testcase);
           //String nameSteps=stepName.toString();
           //System.out.println(nameSteps);
           if (testcase.equalsIgnoreCase(stepName)) {
               System.out.println(a);
               testBase.writeData(System.getenv("ReportDataPath"),"ReportFile",a,1,"pass");
               a++;
           }
           else{
               System.out.println(a);
              // testBase.writeData(System.getenv("ReportDataPath"),"ReportFile",a,1,"pass");
               System.out.println("Test Case Not found");
               a++;
           }
       }
       else{
           System.out.println(a);
          //String nameSteps=stepName.toString();
           if (testcase.equalsIgnoreCase(stepName)) {
               testBase.writeData(System.getenv("ReportDataPath"),"ReportFile",a,1,"fail");
               a++;
           }
           else{
               System.out.println(a);
               System.out.println("Test Case Not found");
               //testBase.writeData(System.getenv("ReportDataPath"),"ReportFile",a,1,"fail");
               a++;
           }
       }
   }
*/
   //............................................................................................................

    // Close the webDriver instance
    @AfterSuite
    public void closeDriver() {
        //webDriver.quit();
    }

}
