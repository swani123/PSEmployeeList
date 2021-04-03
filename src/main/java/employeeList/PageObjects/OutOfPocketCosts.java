package employeeList.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.ExtentTest;

import employeeList.Utilities.EmployeeData;
import employeeList.commonActions.BrowserActions;
import employeeList.commonActions.ExtentReportScript;

public class OutOfPocketCosts extends BrowserActions {

	ExtentTest longTermProjectionTest;
	ExtentReportScript extentReport;

	public OutOfPocketCosts(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/ul/li[4]/a[2]")
	WebElement LongTermCareProjectionTab;

	public EmployeeData runOutOfPocketCosts(EmployeeData emplRec, ExtentReportScript extnReport) throws Exception {

		extentReport = extnReport;
		click(LongTermCareProjectionTab);
		Thread.sleep(2000);

		return emplRec;
	}

}
