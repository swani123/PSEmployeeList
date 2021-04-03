package employeeList.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import employeeList.commonActions.BrowserActions;



public class LoginPage extends BrowserActions {
	public WebDriver driver;
	public LoginPage(WebDriver driver)  {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(how = How.ID, using="loginEmail")
	WebElement loginID;
	
	@FindBy(how = How.ID, using="loginPassword")
	WebElement password;
	@FindBy(how = How.ID, using="loginButton")
	WebElement loginBtn; 
	
	public void loginDetails(String login,String passwrd) {
		
		loginID.sendKeys(login);
		password.sendKeys(passwrd);
		loginBtn.click();
		
	}
	

}
