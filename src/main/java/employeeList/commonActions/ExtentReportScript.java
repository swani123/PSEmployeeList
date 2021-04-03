package employeeList.commonActions;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportScript {

    public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void setExtent() {
//		  Date date =  new Date();
		  String formattedDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
		  
//		String reportFileName = "/test-output/reports/TestReport"+formattedDate+".html";
		String reportFileName = "/test-output/TestReport"+formattedDate+".html";
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+reportFileName);
        htmlReport.config().setDocumentTitle("PlanSelect EmployeeList"); 
        htmlReport.config().setReportName("Automation Report");
        htmlReport.config().setTheme(Theme.STANDARD);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReport);
        
        extent.setSystemInfo("OS", "MacOS");
        extent.setSystemInfo("Tester Name", "Sirisha Kota");
        extent.setSystemInfo("Browser", "Chrome");
	}
	
	public void writeToReport() {
		extent.flush();
	}
	public ExtentTest createTest(String testName) {
		ExtentTest test;
		test = extent.createTest(testName);
		return test;
	}
	public void extentLog(String status, ExtentTest logger, String testCase) {
	if(status.equals("PASS")) {
	  logger.log(Status.PASS, "Test Case Passed. "+testCase);
	}
	else { 
		  logger.log(Status.FAIL, "Test Case Failed. "+testCase);	
	}
	}
	public void reportTestResult(ITestResult result) throws IOException{
		
		if(result.getStatus() == ITestResult.FAILURE) 
		{
			test.log(Status.FAIL, "Test Case is Failed");
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case is Skipped");		
		}
		else if (result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.SKIP, "Test Case is Success");			
		}
	}
	
	public static String getScreenshot(WebDriver driver, String ScreenShotName) {
	  Date date = (Date) new java.util.Date();
	  String formattedDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
	  TakesScreenshot ts = (TakesScreenshot) driver;
	  File source = ts.getScreenshotAs(OutputType.FILE);
	
		String destination = System.getProperty("user.dir")+"/test-output/ScreenShots/"+ScreenShotName+date+".jpg";
		File finalDestination = new File(destination);
//		FileUtils.copyFile(source,finalDestination);
		return  destination;
	}

}
