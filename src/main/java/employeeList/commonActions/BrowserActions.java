package employeeList.commonActions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrowserActions {

	private static final Exception e = null;
	public static WebDriver driver;
	public Properties prop;

	public static BrowserActions browse;
	public static SoftAssert s_assert = new SoftAssert();
	String filePath = System.getProperty("user.dir") + "\\Screenshots\\";

	public BrowserActions(WebDriver driver) {
		driver = driver;
		// TODO Auto-generated constructor stub
	}

	// to refeesh the web page
	public void refreshpage() throws InterruptedException {
		// Log.info("Refreshing web page");
		driver.navigate().refresh();
		Thread.sleep(5000);

	}

	// this method gets the page title
	public void verifyPageTitle(String expectedTitle) throws InterruptedException {
		String actualTitle = driver.getTitle();
		browse.wait(5000);
		Assert.assertEquals(actualTitle, expectedTitle);
		// Log.exception("The title " + actualTitle + " is not Expected Title", e);
	}

	// this methos switches to the located frame in webpage

	public void frameSwitch() {
		driver.switchTo().frame(driver.findElement(By.id("id")));

	}

	// this method waits for element to be present by locator of element from page
	// class
//	public void waitForPresenseOfElement(WebElement elm) {
//		WebDriverWait mywait = new WebDriverWait(driver, 10);
//		mywait.until(ExpectedConditions.presenceOfElementLocated(elm));
//	}

	// this method waits for the element to be clickableby locator of the element
	// from page class
	public WebElement waitForelementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitAndClick(By by) {
		waitForelementToBeClickable(by).click();
	}

	// @param locator of the element from the page class

	public void jScriptClick(By by) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	/**
	 * This method causes web driver to wait for mentioned time period. If web
	 * driver can not find element in starting, it will wait for specified time
	 * duration without searching for element during wait period. Once wait is over,
	 * web driver will try to find an element for last time before throwing
	 * exception.
	 */
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	/**
	 * This method forces web driver to wait for mentioned time period. This wait
	 * should be used in worst case.
	 */

	public void threadSleepWait(int timeInMilliSeconds) {
		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method hovers the cursor on the located element. Here it is used for
	 * locating first and second plan on the home page.
	 * 
	 * @param by locator of element from page class
	 */
	public void mouseOver(By by) {
		WebElement move = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(move).perform();
		threadSleepWait(1000);
	}

	public void webTableElements(By by1, By by2) {
		WebElement htmlTable = driver.findElement(by1);
		List<WebElement> rows = (List<WebElement>) htmlTable.findElement(by2);
		for (int rownumber = 0; rownumber < rows.size(); rownumber++) {
			System.out.println(rows.size());
		}
	}

	/**
	 * This method hovers the cursor on web element.
	 * 
	 * @param by locator of element from page class
	 */
	public void mouseHoverToElement(WebElement by) {
		Actions action = new Actions(driver);
		action.moveToElement(by).perform();
		threadSleepWait(1000);
	}

	/**
	 * This method performs mouse click on the located element.
	 * 
	 * @param by locator of element from page class
	 */

	public void moveFocusAndClick(By by) {
		WebElement move = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(move).click().perform();
	}

	public void mouseHoverCoordinates() {
		new Actions(driver).moveByOffset(652, 96).click().perform();

	}

	/**
	 * This method clicks on the keyboard's ESC Key.
	 */

	public void pressEscKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();

	}

	/**
	 * This method clicks on the keyboard's ENTER Key.
	 */

	public void pressEnterKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * This method takes the screen shot of the page and stores it in .jpg file in
	 * the project. The file is visible after refreshing the project.
	 */

	public void takeScreenshot(String methodName) throws IOException {
		File getImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("userdir") + "\\screenshots\\";
//			FileUtils.copyFile(getImage, new File(filePath + methodName + ".png"));
		System.out.println("***Placed screen shot in " + filePath + " ***");
	}

	/**
	 * This method waits for page to load till mentioned timeout. Before waiting it
	 * verifies whether page is in complete state.
	 * 
	 * @param timeOutInSeconds seconds to wait for page to load
	 */
	public void waitForPageToLoad(int timeOutInSeconds) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "return document.readyState";
		// Check the readyState before doing any waits
		if (js.executeScript(command).toString().equals("complete")) {
			return;
		}

		for (int i = 0; i < timeOutInSeconds; i++) {
			try {
				Thread.sleep(1000);
				if (js.executeScript(command).toString().equals("complete")) {
					break;
				}
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * This method navigates to back page by clicking browser back button
	 */

	public void browserBackButton() {
		driver.navigate().back();
		threadSleepWait(1000);
	}

	/**
	 * This method verifies whether element is displayed or not and returns true or
	 * false
	 * 
	 * @param by locator of element from page class
	 * @return True or False
	 */

	public boolean isElementDisplayed(By by) {
		return driver.findElement(by).isDisplayed();
	}

	/**
	 * This method verifies whether web element is displayed or not. It returns true
	 * / false
	 * 
	 * @param w It takes web element as parameter
	 * @return true if web element is displayed
	 */

	public static float getFieldValue(WebElement elm) {

		String value = elm.getText().replaceAll("$", "");
		float amount = (float) 0.00;
		try {
			amount = Float.parseFloat(value);
		} catch (Exception e) {

		}

		return amount;
	}

	public boolean isElementDisplayed(WebElement w) {
		return w.isDisplayed();
	}

	/**
	 * This method verifies whether element is present or not and returns true or
	 * false
	 * 
	 * @param by locator of element from page class
	 * @return True or False
	 */

	public boolean isElementPresent(By by) {
		return driver.findElements(by).size() != -0;
	}

	// assertions

	public void AssertTextBoxValue(By by) {
		WebElement input = driver.findElement(by);
		// Log.info(input.getAttribute("value"));
		s_assert.assertNotNull(by);
		s_assert.assertEquals(input.getAttribute("value"), "");

	}

	public void assertValue(By by, String value) {
		WebElement input = driver.findElement(by);
		// Log.info("The value of " + by + " is " + input.getAttribute("value"));
		s_assert.assertNotNull(input);
		s_assert.assertEquals(input.getAttribute("value"), value);

	}

	public String getText(WebElement elm) {
		// waitForPresenseOfElement(elm);
		// Log.info("\"Getting text of \" + by + \" element\"");
		// String text = driver.findElement(elm).getText();
		return elm.getText();
		// Log.info("The text of element is '" + text + "'");
		// return text;
	}

	/**
	 * This method clicks on the located element. Before clicking, web driver waits
	 * for element to be present and to be clickable. After that it waits for wait
	 * mentioned in thread sleep and clicks on element.
	 * 
	 * @param elm Takes the locator from page class
	 */

	public void click(WebElement elm) {
		// WebElement element = driver.findElement(by);
		// Actions action = new Actions(driver);
		// action.moveToElement(element).perform();
		// waitForPresenseOfElement(by);
		// waitForelementToBeClickable(by);
		elm.click();

	}

	public void ClickJS(By by) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click()", driver.findElement(by));
	}

	public void getWindowHandle(By by) {
		// waitForPresenseOfElement(by);
		waitForelementToBeClickable(by);
		driver.getWindowHandle();
	}

	/**
	 * This method writes the text in located element. Before writing, it waits for
	 * that element to be present and clickable. Then it clears that field and fill
	 * up with new value.
	 * 
	 * @param by   takes locator from page class
	 * @param text value to enter in text field
	 */

	public void type(WebElement elm, String text) {
		// waitForPresenseOfElement(by);
//		waitForelementToBeClickable(by);
		elm.clear();
		elm.sendKeys(text);

	}

	/**
	 * This method finds the web elements present by given locator.
	 * 
	 * @param by locator of element from page class
	 * @return list of elements present by given locator
	 */
	public List<WebElement> getListOfElements(By by) {
		List<WebElement> myElement = driver.findElements(by);
		System.out.println(myElement);
		return myElement;
	}

	/**
	 * This method locates subsequent elements of a parent class
	 * 
	 * @param by1 Parent class web element
	 * @param by2 Child class web element
	 * @return Subsequent element's list of parent class elements
	 */
	public List<WebElement> getElementList(WebElement by1, By by2) {
		List<WebElement> myElements = by1.findElements(by2);
		System.out.println(myElements);
		return myElements;
	}

	/**
	 * This method waits for element to become invisible on UI.
	 * 
	 * @param by      locator of element from page class
	 * @param seconds wait time in seconds
	 * @return
	 */

	public boolean waitForElementToBeInvisible(By by, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		Boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		return element;
	}

	/**
	 * This method causes web driver to wait for element to be visible till
	 * mentioned timeout
	 * 
	 * @param by locator of element from page class
	 */
	public void waitForElementToBeVisible(By by) { // Not using currently
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * This method handles the loading symbol state. If it is displayed in starting,
	 * it will wait for it to be invisible for mentioned timeout. If not then it
	 * will wait for 2 seconds and go to next step.
	 * 
	 * @param by      loading symbol
	 * @param Seconds timeout to wait
	 */

	public void loadingSymbolState(By by, int Seconds) {
		if (isElementDisplayed(by)) {
			waitForElementToBeInvisible(by, Seconds);
		} else {
			threadSleepWait(3000);
		}
	}

	public void assertObject(By by) {
		threadSleepWait(200);
		implicitWait();
		s_assert.assertEquals(isElementDisplayed(by), true);

	}

	public void assertObjectForPopup(By by) {
		threadSleepWait(200);
		implicitWait();
		// add loop to check true value for
		long time = System.currentTimeMillis();
		for (;;) {
			long time2 = System.currentTimeMillis();
			System.out.println();
			boolean returnValue = isElementDisplayed(by);
			if (time2 - time > 5000 || returnValue) {
				// System.out.println("inside break loop = " +returnValue);
				break;
			}
		}
		s_assert.assertEquals(isElementDisplayed(by), true);
		System.out.println(by.toString() + "  " + isElementDisplayed(by));
	}

	public void assertCheckBox(By by) {
		threadSleepWait(200);
		implicitWait();
		s_assert.assertEquals(isElementDisplayed(by), true);

	}

	public boolean isElementSelected(WebElement doNotContributeToHSAFSA) {
//		return driver.findElement(doNotContributeToHSAFSA).isSelected();
		return doNotContributeToHSAFSA.isSelected();
	}

	/**
	 * This method checks for new tab opened after clicking on web element. Asserts
	 * the page title and closes the new tab.
	 * 
	 * @param expected the title to be verified
	 */

//	public void checkNewTabTitle(String title) {
//		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(browserTabs.get(1));
//		System.out.println(title);
//		try {
//			verifyPageTitle(title);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		threadSleepWait(5000);
//		try {
//			closeDriver();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		driver.switchTo().window(browserTabs.get(0));
//		threadSleepWait(5000);
//	}

	/**
	 * This method checks for new tab opened after clicking on web element. Asserts
	 * the page title and closes the new tab.
	 * 
	 * @param expected the title to be verified
	 * @throws InterruptedException
	 */

//	public void checkNewTab() throws InterruptedException {
//
//		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(browserTabs.get(1));
//		threadSleepWait(10000);
//		closeDriver();
//		driver.switchTo().window(browserTabs.get(0));
//		threadSleepWait(2000);
//	}

	/**
	 * This method checks for new tab opened after clicking on web element. And
	 * returns the list of browser tabs.
	 * 
	 * @param expected the title to be verified
	 */
	public List<String> checkNewTab1() {

		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
		threadSleepWait(5000);
		return browserTabs;
	}

//	public void closeTab(List<String> browserTabs) throws InterruptedException {
//		closeDriver();
//		driver.switchTo().window(browserTabs.get(0));
//		threadSleepWait(2000);
//	}

	public void clickOkButton() {
		driver.switchTo().alert().accept();
	}

	public void waitForLoadingPlans(By by) {
		if (isElementPresent(by)) {
			waitForElementToBeInvisible(by, 10);
		} else {
			threadSleepWait(2000);
		}
	}

	/**
	 * This method selects drop down option by matching with text.
	 * 
	 * @param by     Locates the drop down box
	 * @param option Passes text to select from drop down
	 */
	public void selectDropDownMenu(By by, String option) {
		WebElement element = driver.findElement(by);
		Select se = new Select(element);
		se.selectByVisibleText(option);
		implicitWait();
	}

	public WebElement selectDropDownIndex(By by, Integer option) {
		WebElement element = driver.findElement(by);
		Select se = new Select(element);
		se.selectByIndex(option);
		implicitWait();
		return element;
	}

	public void selectDropDownValue(By by, String option) {
		WebElement element = driver.findElement(by);
		Select se = new Select(element);
		se.selectByValue(option);
		implicitWait();

	}

//	public void selectJcfDropdownValue(By by, String option) throws Exception {
//		Thread.sleep(4000);
//		click(by);
//		By applicationValue = By.xpath("//span[contains(text(),'" + option + "')]");
//		click(applicationValue);
//	}
//
//	public void selectJcfDropdown2Value(By by1, By by2, String option) {
//		click(by1);
//		//waitForPresenseOfElement(by2);
//		// waitForelementToBeClickable(by2);
//		Select se = new Select(driver.findElement(by2));
//		se.selectByValue(option);
//		//waitForPresenseOfElement(by2);
//		waitForelementToBeClickable(by2);
//		driver.findElement(by2).click();
//		// se.selectByValue(option);
//		// se.selectByVisibleText("");
//
//	}
//
//	public void selectJcfDropdownIndex(By by, Integer option) {
//		click(by);
//		By applicationValue = By.xpath("//span[contains(text(),'" + option + "')]");
//		click(applicationValue);
//	}

	public void assertSelectedRadioButton(By by) {
		driver.findElement(by).isSelected();
	}

	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Log.info("Scrolling up page");
		jse.executeScript("scroll(0, -450)");
	}

	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Log.info("Scrolling up page");
		jse.executeScript("scroll(0, 500)");
	}

//	public void assertText(By by, String str) {
//		s_assert.assertEquals(getText(by), str);
//
//	}

	public boolean getReportFileStatus() {
		String folderName = System.getProperty("user.dir") + "\\Downloads"; // Give your folderName
		File[] listFiles = new File(folderName).listFiles();

		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].isFile()) {
				String fileName = listFiles[i].getName();
				if (fileName.startsWith("individualExpenseReport") && fileName.endsWith(".csv")) {
					System.out.println("found file" + " " + fileName);
					System.out.println("File Size is: " + fileName.length());
					s_assert.assertNotNull(fileName.length(), "The file size assertion is passed");
				}
			}
		}
		return true;

	}

	public void assertDownloadedFile() {
		s_assert.assertTrue(getReportFileStatus(), "Failed to download Expected document");
	}

	public Properties getProp() {
		return prop;
	}

	public void selectDataByJS(By by, String dateVal) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement element = driver.findElement(by);
		js.executeScript("arguments[0].setAttribute('Value','" + dateVal + "');", element);

	}

	public void FluentWait(final String path) {

		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(90, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement content = fluentWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(path));
			}
		});

	}

	public Float getFieldValueNumber(WebElement Elm) {

		//String fldValue = getText(Elm).strip();
		String fldValue = getText(Elm).trim();
		String amtString = null;

		float amount = (float) 0.00;
		if (fldValue != null && !(fldValue.contentEquals(" "))) {
			try {
       		    amtString = fldValue.substring(1).replaceAll("\nDetails","").replaceAll(",", "");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				return amount;
			}

			try {
				amount = Float.parseFloat(amtString);
			} catch (Exception e) {
				System.out.println("Number format Excetion :" + e.getMessage());
				return amount;
			}
		}

		return amount;
	}

	public int getPlanCount(WebElement Elm) {

		String[] strArray = getText(Elm).split(":");
		int planCount = 0;

		//String valueStr = strArray[1].strip();
		String valueStr = strArray[1].trim();

		try {
			planCount = Integer.parseInt(valueStr);
		} catch (Exception e) {
			System.out.println("Number format Excetion :" + e.getMessage());
		}

		return planCount;
	}
	
	public int getNumberValue(WebElement Elm) {
		int value=0;

		try {
			value = Integer.parseInt(Elm.getAttribute("value"));
		} catch (Exception e) {
			System.out.println("Number format Excetion :" + e.getMessage());
		}
		return value;
		
	}

}
