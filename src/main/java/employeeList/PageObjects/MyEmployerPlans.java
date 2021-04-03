package employeeList.PageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


import employeeList.Utilities.EmployeeData;
import employeeList.commonActions.BrowserActions;
import employeeList.commonActions.ExtentReportScript;

public class MyEmployerPlans extends BrowserActions {
	 ExtentTest myEmployerPlansTest;

	 
	 ExtentReportScript extentReport;
	
	public MyEmployerPlans(WebDriver driver)   {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/div/section/div[5]/div[2]/table/tbody/tr[1]/td[2]")
	WebElement HealthcarePlanPremium1;
	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/div/section/div[5]/div[2]/table/tbody/tr[1]/td[3]")
	WebElement HealthcarePlanPremium2;
	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/div/section/div[5]/div[2]/table/tbody/tr[1]/td[4]")
	WebElement HealthcarePlanPremium3;
	
//	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/div/section/div[5]/div[1]/div[2]/table/tbody/tr[3]/td[3]")
//	WebElement CarrierName;
	@FindBy(how = How.XPATH, using="//*[@id=\"selectCarrierName-0\"]")
	WebElement CarrierName1;
	@FindBy(how = How.XPATH, using="//*[@id=\"selectCarrierName-1\"]")
	WebElement CarrierName2;
	@FindBy(how = How.XPATH, using="//*[@id=\"selectCarrierName-2\"]")
	WebElement CarrierName3;
	@FindBy(how = How.XPATH, using="//*[@id=\"selectPlanPlanName-0\"]")
	WebElement PlanName1;
	@FindBy(how = How.XPATH, using="//*[@id=\"selectPlanPlanName-1\"]")
	WebElement PlanName2;
	@FindBy(how = How.XPATH, using="//*[@id=\"selectPlanPlanName-2\"]")
	WebElement PlanName3;
	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/div/section/div[5]/div[1]/div[1]/ul/li[2]/span")
	WebElement PlanCount;	
	@FindBy(how = How.XPATH, using="//*[@id=\"paginationNextButton\"]/span")
	WebElement scrollRight;
	@FindBy(how = How.ID, using="showOverrideHsaDialog")
	WebElement HSAFSAContribution;
	@FindBy(how = How.ID, using="overrideHsaFsaDoNotContributeRadio")
	WebElement DoNotContributeToHSAFSA;
	@FindBy(how = How.ID, using="overrideHsaFsaSelectButton")
	WebElement selectButton;
	@FindBy(how = How.XPATH, using="//*[@id=\"overrideHsaFsaCancelIcon\"]/md-icon")
	WebElement closeButton;
	
	public EmployeeData runEmployeePlans(EmployeeData emplRec, ExtentReportScript extnReport) throws InterruptedException {
		extentReport = extnReport;
	
		Thread.sleep(3000);
		emplRec.healthcarePlanPremium =	getFieldValueNumber(HealthcarePlanPremium2);
		emplRec.carrierName =	getText(CarrierName2);
		emplRec.planName =	getText(PlanName2);
		int recPlanCount = getPlanCount(PlanCount);
		System.out.println("Plan Count :"+recPlanCount);
		
//		checkforZeroValue(emplRec.emailID,HealthcarePlanPremium1,CarrierName1,PlanName1);
		checkforZeroValue(emplRec.emailID,HealthcarePlanPremium2,CarrierName2,PlanName2);	
//		
//		int scrollCount = recPlanCount - 2;
//		do {
//			checkforZeroValue(emplRec.emailID,HealthcarePlanPremium3,CarrierName3,PlanName3);
//			click(scrollRight);
//			Thread.sleep(1000);
//			scrollCount--;
//		}while(scrollCount>0);
		
		Thread.sleep(1000);
		overrideHsaFsaContr();

		return emplRec;
	}
		
	public void overrideHsaFsaContr() throws InterruptedException {
		click(HSAFSAContribution);
		Thread.sleep(1500);
		if(isElementSelected(DoNotContributeToHSAFSA)==true) {
			click(closeButton);
			}else
		{
			click(DoNotContributeToHSAFSA);
			click(selectButton);
			}
		
	}
	public void checkforZeroValue(String emplId,WebElement hcPremium,WebElement carrierName,WebElement planName) {
		float amount = getFieldValueNumber(hcPremium);

		if(amount==0) {
			myEmployerPlansTest = extentReport.createTest("Check value 0: "+emplId+" "+getText(carrierName)+" "+getText(planName));
			extentReport.extentLog("FAIL", myEmployerPlansTest, "HealthCare Premium Value :"+amount);
		}
	}

}
