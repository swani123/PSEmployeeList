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

public class FiveYearProjection extends BrowserActions{
	 ExtentTest fiveYearProjectionTest;	 
	 ExtentReportScript extentReport;
	 
	public FiveYearProjection(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/ul/li[5]/a[2]")
	WebElement FiveYearProjection;
	@FindBy(how = How.XPATH, using="//*[@id=\"tab1_2\"]/div/header/ul/li[1]/div/strong")
	WebElement totalHealthPlanPremium;
	
	public EmployeeData runFiveYearProjection(EmployeeData emplRec,ExtentReportScript extnReport) throws Exception
	
	{

		extentReport = extnReport;
		click(FiveYearProjection);
		Thread.sleep(5000);
		
		emplRec.totalHealthPlanPremium = checkforZeroValue(emplRec.emailID,"Total Health Plan Premium",totalHealthPlanPremium);;
		
		return emplRec;
		
	}
	public float checkforZeroValue(String emplId, String expenseType, WebElement Amt) {
		float amount = getFieldValueNumber(Amt);

		if (amount == 0) {
			fiveYearProjectionTest = extentReport.createTest("Check value 0: " + emplId + " " + expenseType);
			extentReport.extentLog("FAIL", fiveYearProjectionTest, expenseType + " :" + amount);
		}
		return amount;
	}

}
