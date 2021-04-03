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

public class ProjectedBenefitConsumption extends BrowserActions{
	 ExtentTest benefitConsumptionTest;	 
	 ExtentReportScript extentReport;
	 
	public ProjectedBenefitConsumption(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(how = How.CSS, using="#main > div.container.ng-scope > div > div > ul > li:nth-child(3) > a:nth-child(2)")
	WebElement ProjectedBenefitConsumptionTab;
	@FindBy(how = How.XPATH, using="//*[@id=\"usageTextBox-0\"]")
	WebElement DoctorVisit;
	@FindBy(how = How.ID, using="usageTextBox-1")
	WebElement DrugAndPrescriptions;
	@FindBy(how = How.ID, using="usageTextBox-2")
	WebElement HospitalInpatient;
	@FindBy(how = How.ID, using="usageTextBox-3")
	WebElement HospitalOutpatient;
	@FindBy(how = How.ID, using="usageTextBox-4")
	WebElement LabsAndequipment;
	
	public EmployeeData runBenefitConValues(EmployeeData emplRec,ExtentReportScript extnReport) throws Exception
	
	{

		extentReport = extnReport;
		click(ProjectedBenefitConsumptionTab);
		Thread.sleep(2000);
		click(DoctorVisit);
		float docVisit= getNumberValue(DoctorVisit);
		System.out.println("Doctor Visit :"+DoctorVisit.getAttribute("value"));

		
		emplRec.doctorVisit = docVisit;
		click(DoctorVisit); checkforZeroValue(emplRec.emailID,"Doctor Visit",DoctorVisit);
		click(DrugAndPrescriptions); checkforZeroValue(emplRec.emailID,"Drugs & Prescription",DrugAndPrescriptions);
		click(HospitalInpatient); checkforZeroValue(emplRec.emailID,"Hospital Inpatient",HospitalInpatient);
		click(HospitalOutpatient); checkforZeroValue(emplRec.emailID,"Hospital Outpatient",HospitalOutpatient);
		click(LabsAndequipment); checkforZeroValue(emplRec.emailID,"Labs& Equipment",LabsAndequipment);
		
		return emplRec;
		
	}
	public void checkforZeroValue(String emplId,String benefitType, WebElement benefitAmt) {
		float amount = getNumberValue(benefitAmt);

		if(amount==0) {
			
			benefitConsumptionTest = extentReport.createTest("Check value 0: "+emplId+" "+benefitType);
			extentReport.extentLog("FAIL", benefitConsumptionTest, benefitType+" :"+amount);
		}
	}

}
