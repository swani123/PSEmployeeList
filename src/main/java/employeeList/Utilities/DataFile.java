package employeeList.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class DataFile {
	
	InputStream inputstream;
	Properties config=new Properties();
	EmployeeData inputData[]=null;
	
	public EmployeeData[] readExcel() throws Exception {
		String fileName="EmployeeListInput.xlsx";
		File file=new File(System.getProperty("user.dir")+"/src/main/java/employeeList/Utilities/" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook workBook=new XSSFWorkbook(inputStream);
		if(workBook != null) {
			String sheetId;
			
			XSSFSheet workSheet = workBook.getSheetAt(0);
			if(workSheet != null) {
				int rowCount = workSheet.getLastRowNum() - workSheet.getFirstRowNum();
				inputData = new EmployeeData[rowCount];
				for(int i=1;i<rowCount+1;i++) {
					
					inputData[i-1]=new EmployeeData();
					Row row = workSheet.getRow(i);
					int lastColPos = row.getLastCellNum();
					for(int j=0;j<lastColPos;j++) {
						String cellValue;
						//cellValue = row.getCell(j).getStringCellValue();
						switch(j) {
						case 0:
							inputData[i-1].emailID=row.getCell(j).getStringCellValue();
							break;
						case 1:
							inputData[i-1].password=row.getCell(j).getStringCellValue();
							break;
						case 2:
//			    			inputData[i-1].enrollmentyear=Integer.toString((int) row.getCell(j).getNumericCellValue());
			    			inputData[i-1].enrollmentyear=Integer.toString(Math.round((int) row.getCell(j).getNumericCellValue()));
//							inputData[i-1].enrollmentyear=row.getCell(j).getStringCellValue();
			    			break;
						}
						
						
						
					}
				}
				
			}
			
		}
		return inputData;
		
	}
	public XSSFWorkbook initializeOutputFile() {
		
		XSSFWorkbook workBook=new XSSFWorkbook();
		return workBook;
		
	}
	
	public XSSFSheet createSheet(XSSFWorkbook workbook) {
		XSSFSheet sheet = workbook.createSheet("EmployeeListResult");
		return sheet;
		
	}
	
	public void createHeaderRow(XSSFSheet sheet) {
		
		XSSFRow headerRow = sheet.createRow(0);
        int cellIndex = 0;
        headerRow.createCell(cellIndex).setCellValue("Email ID"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("Contribution Profile Scope"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("Carrier Name"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("Plan Name"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("HealthCarePlanPremium"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("Doctor Visit"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("TotalHealthPlanPremium"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("PvAsOfYearStart"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("TotalHealthCareExpenses"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("PvAsOfYearEnd"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("MedicareExpenses"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("TotalSelectedExpensesPrimary"); cellIndex++;
        headerRow.createCell(cellIndex).setCellValue("TotalSelectedExpensesSpouse"); cellIndex++;

        }
	
	public void addRecord(XSSFSheet sheet, EmployeeData outputRec, int rowIndex) {
		int cellIndex = 0;
        XSSFRow row = sheet.createRow(rowIndex);


        row.createCell(cellIndex).setCellValue(outputRec.emailID); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.contributionProfileScope); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.carrierName); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.planName); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.healthcarePlanPremium); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.doctorVisit); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.totalHealthPlanPremium); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.pvAsofYearStart); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.totalHealthCareExpenses); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.pvAsofYearEnd); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.medicareExpenses); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.totalselectedExpensesPrimary); cellIndex++;
        row.createCell(cellIndex).setCellValue(outputRec.totalselectedExpensesSpouse); cellIndex++;
        
		
	}
	
	public void closeOutputFile(XSSFWorkbook workBook,String fileName) throws Exception {
	     FileOutputStream fileOut = new FileOutputStream(fileName);
	        workBook.write(fileOut);
	        fileOut.close();
	        workBook.close();
	}

}
