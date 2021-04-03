package employeeList.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import employeeList.commonActions.WrapperClass;

public class EmployeeTest extends BaseTest {
	

	
  @BeforeClass
	public void initialize() throws IOException {
		initConfig();
		initilization();
	}
  @Test
  public void runEmployeeList() throws Exception {
	    WrapperClass wc = PageFactory.initElements(driver, WrapperClass.class);	
        wc.runEmloyeeProfiles(prop);
  }
}
