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

public class LifeTimeExpensePage extends BrowserActions {
	ExtentTest lifetimeExpenseTest;
	ExtentReportScript extentReport;

	public LifeTimeExpensePage(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/ul/li[6]/a[2]")
	WebElement LifeTimeExpenseProjectionTab;
	@FindBy(how = How.XPATH, using = "//*[@id=\"tab1_2\"]/div/header/ul[1]/li[1]/div/strong")
	WebElement pvAsofYearStart;
	@FindBy(how = How.XPATH, using = "//*[@id=\"tab1_2\"]/div/header/ul[4]/li[1]/div/strong")
	WebElement pvAsofYearEnd;
	@FindBy(how = How.XPATH, using = "//*[@id=\"tab1_2\"]/div/header/ul[1]/li[2]/div/strong")
	WebElement totalHealthCareExpenses;
	@FindBy(how = How.XPATH, using = "//*[@id=\"tab1_2\"]/div/header/ul[4]/li[2]/div/strong")
	WebElement medicareExpenses;
	@FindBy(how = How.XPATH, using = "//*[@id=\"tab1_2\"]/div/header/ul[2]/li/div/strong")
	WebElement totalHealthplanPremium;

	public EmployeeData runLifeTimeExpenseProjection(EmployeeData emplRec, ExtentReportScript extnReport)
			throws Exception {

		extentReport = extnReport;
		click(LifeTimeExpenseProjectionTab);
		Thread.sleep(40000);

		emplRec.pvAsofYearStart = checkforZeroValue(emplRec.emailID, "PV As of Year Start", pvAsofYearStart);
		emplRec.pvAsofYearEnd = checkforZeroValue(emplRec.emailID, "PV As of Year End", pvAsofYearEnd);
		emplRec.totalHealthCareExpenses = checkforZeroValue(emplRec.emailID, "Total HealthCare Expenses",
				totalHealthCareExpenses);
		emplRec.medicareExpenses = checkforZeroValue(emplRec.emailID, "Medicare Expenses", medicareExpenses);


		return emplRec;
	}

	public float checkforZeroValue(String emplId, String expenseType, WebElement Amt) {
		float amount = getFieldValueNumber(Amt);

		if (amount == 0) {
			lifetimeExpenseTest = extentReport.createTest("Check value 0: " + emplId + " " + expenseType);
			extentReport.extentLog("FAIL", lifetimeExpenseTest, expenseType + " :" + amount);
		}
		return amount;
	}

}
