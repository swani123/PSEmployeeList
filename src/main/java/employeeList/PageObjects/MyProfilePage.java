package employeeList.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import employeeList.Utilities.EmployeeData;
import employeeList.commonActions.BrowserActions;

public class MyProfilePage extends BrowserActions{

	public MyProfilePage(WebDriver driver) throws IOException  {
		super(driver);		
		// TODO Auto-generated constructor stub
	}
	@FindBy(how = How.XPATH, using="//*[@id=\"main\"]/div[2]/div/div/ul/li[1]/a[2]")
	WebElement myProfileTab;
	@FindBy(how = How.ID, using="planEnrollmentYear")
	WebElement EnrollmentYear;

	@FindBy(how = How.ID, using="myprofile_previous_button")
	WebElement SaveEmployee;
	@FindBy(how = How.ID, using="myprofile_showmemyhealthplan_button")
	WebElement ShowRecommendations;
	@FindBy(how = How.ID, using="myprofile_refresh_recommendations_button")
	WebElement RefreshRecommendations;
	@FindBy(how = How.ID, using="myprofile_showmeexchangeplan_button")
	WebElement ShowExchangePlans;
	@FindBy(how = How.ID, using="myprofile_refresh_exchange_plans_button")
	WebElement RefreshExchangePlans;
	@FindBy(how = How.ID, using="myprofile_generate_pdf_button")
	WebElement GeneratePDF;
	@FindBy(how = How.ID, using="myprofile_retirement_expense_projection_button")
	WebElement LifeTimeExpenseProjection;
	@FindBy(how = How.ID, using="myprofile_long_lerm_care_projection_button")
	WebElement LongTermCareProjection;
	@FindBy(how = How.ID, using="myprofile_cancel_button")
	WebElement Cancel;
	public void goToMyProfile(EmployeeData profileData) throws InterruptedException {
        Thread.sleep(2000);
        myProfileTab.click();
        //type(EnrollmentYear,profileData.enrollmentyear);

        }
	public void updateEnrollYear(EmployeeData profileData) throws InterruptedException {
		Thread.sleep(3000);
       //type(EnrollmentYear,profileData.enrollmentyear);	
        }
	public void refreshRecommendations() throws InterruptedException {
        Thread.sleep(2000);
		RefreshRecommendations.click();

	}
	
	public void refreshExchangePlans() throws InterruptedException {
        Thread.sleep(2000);
		RefreshExchangePlans.click();
	}
	
	
	
}
