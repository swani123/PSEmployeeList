package employeeList.commonActions;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import employeeList.PageObjects.FiveYearProjection;
import employeeList.PageObjects.LifeTimeExpensePage;
import employeeList.PageObjects.LoginPage;
import employeeList.PageObjects.LongTermCareProjection;
import employeeList.PageObjects.MyEmployerPlans;
import employeeList.PageObjects.MyProfilePage;
import employeeList.PageObjects.OutOfPocketCosts;
import employeeList.PageObjects.ProjectedBenefitConsumption;
import employeeList.Utilities.DataFile;
import employeeList.Utilities.EmployeeData;



public class WrapperClass {
	public static LoginPage Lp;
	public static MyProfilePage Mp;
	public static MyEmployerPlans Ep;
	public static ProjectedBenefitConsumption Pb;
	public static OutOfPocketCosts Op;
	public static LifeTimeExpensePage Le;
	public static LongTermCareProjection Lt;
	public static FiveYearProjection Fp;
//	
	public static ExtentReportScript extentReport;
	public static WebDriver driver;
	Properties prop;
	DataFile file = new DataFile();

	XSSFWorkbook workbook;
	XSSFSheet sheet;

   public static EmployeeData  profileData;
	public WrapperClass(WebDriver driver) {
		this.driver = driver;
		// TODO Auto-generated constructor stub
		Lp = PageFactory.initElements(driver, LoginPage.class);
		Mp = PageFactory.initElements(driver, MyProfilePage.class);
		Ep = PageFactory.initElements(driver, MyEmployerPlans.class);
		Pb = PageFactory.initElements(driver, ProjectedBenefitConsumption.class);
		Op = PageFactory.initElements(driver, OutOfPocketCosts.class);
		Le = PageFactory.initElements(driver, LifeTimeExpensePage.class); //	   runLifeTimeExpenseProjection
		Fp = PageFactory.initElements(driver, FiveYearProjection.class);
		Lt = PageFactory.initElements(driver, LongTermCareProjection.class);
		extentReport = new ExtentReportScript();
	}

	public void runEmloyeeProfiles(Properties prop) throws Exception {

		extentReport.setExtent();
        
		EmployeeData[] inputData;
		this.prop = prop;

		inputData = file.readExcel();
		if (inputData.length > 0) {
			initializeFileOut();
			createFileHeader();
			int recIndex = 1 ;
			for (int i = 0; i < inputData.length; i++) {
				profileData = new EmployeeData();
				goToUrl(prop);
				profileData = inputData[i];
				Lp.loginDetails(profileData.emailID, profileData.password);
				enrollmentYear();
				runRecommendationPlans();
     			addRecord(sheet, profileData, recIndex);
				profileData = inputData[i];
				goToMyProfile();
				runExchangePlans();
     			addRecord(sheet, profileData, recIndex+1);
     			recIndex = recIndex++;
//				closeDriver();
			}
			closeFileOut();
			extentReport.writeToReport();
		}

	}
    public void goToUrl(Properties prop){
		driver.get(prop.getProperty("PlanRecommend"));	
    }

	public void closeDriver() throws InterruptedException {
		//Log.info("closing current browser window.....");
		Thread.sleep(1000);
		driver.close();

	}
	public static void runRecommendationPlans() throws Exception {
		profileData.contributionProfileScope = "Employer Plan";
		Mp.refreshRecommendations();
		Thread.sleep(2000);
		runProfilePlans();
	}

	public static void runExchangePlans() throws Exception {
		profileData.contributionProfileScope = "Exchange Plan";

		Mp.refreshExchangePlans();
		Thread.sleep(2000);
		runProfilePlans();
	}
	public void goToMyProfile() throws InterruptedException {
		Mp.goToMyProfile(profileData);
		
	}
	public void enrollmentYear() throws Exception {
		Mp.updateEnrollYear(profileData);
	}

	public static void runProfilePlans() throws Exception {

	   profileData = Ep.runEmployeePlans(profileData, extentReport);
	   profileData = Pb.runBenefitConValues(profileData, extentReport);
	   profileData = Op.runOutOfPocketCosts(profileData, extentReport);
	   profileData = Fp.runFiveYearProjection(profileData, extentReport);
	   profileData = Le.runLifeTimeExpenseProjection(profileData, extentReport);
	   profileData = Lt.runLongTermProjection(profileData, extentReport);   
	   
	}
	
	public void initializeFileOut() throws Exception {
		workbook = file.initializeOutputFile();
		sheet = file.createSheet(workbook);
	}

	public void createFileHeader() {
		file.createHeaderRow(sheet);
	}

	public void addRecord(XSSFSheet sheet, EmployeeData record, int rowIndex) {
		file.addRecord(sheet, record, rowIndex);
		clearRecord();
	}

	public void closeFileOut() throws Exception {
		String fileName = System.getProperty("user.dir")+prop.getProperty("outputPath") + prop.getProperty("outputFileName");
		file.closeOutputFile(workbook, fileName);
	}
	public void clearRecord() {
		//profileData.emailID = null;
		profileData.password=null;
		profileData.contributionProfileScope = null;
		profileData.carrierName = null;
		profileData.planName = null;
		profileData.healthcarePlanPremium = 0;
		profileData.doctorVisit=0;
		profileData.medicareExpenses=0;
		profileData.pvAsofYearEnd=0;
		profileData.pvAsofYearStart=0;
		profileData.totalHealthCareExpenses=0;
		profileData.totalHealthPlanPremium=0;
		profileData.totalselectedExpensesPrimary=0;
		profileData.totalselectedExpensesSpouse=0;
	}

}
