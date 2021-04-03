package employeeList.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import employeeList.commonActions.BrowserActions;

public class BaseTest {
	
	private static final Exception e = null;
	public static WebDriver driver;
	public Properties prop;

//	public static BrowserActions browse;
	public static SoftAssert s_assert = new SoftAssert();
	String filePath = System.getProperty("user.dir") + "\\Screenshots\\";

	public void initConfig() throws IOException {
		driver = driver;
		prop = new Properties();
		try {
            String path = System.getProperty("user.dir") + "\\Configurations\\config.properties";
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
 
	public void initilization() {
		String browserName = prop.getProperty("browser");

		//Log.info("Launching  " + browserName + " browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					(System.getProperty("user.dir") + "\\drivers\\chromedriver.exe"));
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir" + "\\drivers\\geckodriver.exe"));
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					(System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe"));
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					(System.getProperty("user.dir") + "\\drivers\\MicrosoftWebDriver.exe"));
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	// this method goes to the mentioned url

		public void goToUrl() {
			driver.get(prop.getProperty("PlanRecommend"));
		}

		// this method closes the driver window on which it is working
		public void closeDriver() throws InterruptedException {
			//Log.info("closing current browser window.....");
			Thread.sleep(1000);
			driver.close();

		}

		// this method closes all the windows associated with test and ends the
		// webdriver session
		public void quitDriver() throws InterruptedException {
			//Log.info("Ending test execution and closing browser....");
			Thread.sleep(1000);

			driver.quit();
		}
	
  
}
