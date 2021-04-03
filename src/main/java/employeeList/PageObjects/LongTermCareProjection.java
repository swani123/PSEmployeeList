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

public class LongTermCareProjection extends BrowserActions{
	 ExtentTest longTermProjectionTest;	 
	 ExtentReportScript extentReport;
	 
	public LongTermCareProjection(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/ul/li[7]/a[2]")
	WebElement LongTermCareProjectionTab;
	@FindBy(how = How.XPATH, using="//*[@id=\"tab1_2\"]/div/header/ul/li[1]/div/strong[3]")
	WebElement totalExpensePrimary;
	@FindBy(how = How.XPATH, using="//*[@id=\"tab1_2\"]/div/header/ul/li[2]/div/strong[3]")
	WebElement totalExpensesSpouse;
	
	
	public EmployeeData runLongTermProjection(EmployeeData emplRec, ExtentReportScript extnReport)
			throws Exception {

		extentReport = extnReport;
		click(LongTermCareProjectionTab);
		Thread.sleep(2000);

		emplRec.totalselectedExpensesPrimary = checkforZeroValue(emplRec.emailID, "Total Expenses for Primary", totalExpensePrimary);
		emplRec.totalselectedExpensesSpouse = checkforZeroValue(emplRec.emailID, "Total Expenses for Spouse", totalExpensesSpouse);
	
		return emplRec;
	}

	public float checkforZeroValue(String emplId, String expenseType, WebElement Amt) {
		float amount = getFieldValueNumber(Amt);

		if (amount == 0) {
			longTermProjectionTest = extentReport.createTest("Check value 0: " + emplId + " " + expenseType);
			extentReport.extentLog("FAIL", longTermProjectionTest, expenseType + " :" + amount);
		}
		return amount;
	}	

}
