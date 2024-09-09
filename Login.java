package Login_functionality;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Login {
	WebDriver driver ; 
	
	@BeforeClass
	@parameters({"browser","path"})
	public void pageSetup(String browser, String path, String countrycode) throws InterruptedException {
		
		//Setting up browser through xml file and accessing the application link.
		
		System.setProperty(browser,path);
			driver = new ChromeDriver();
			Thread.sleep(800);
			driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
			driver.manage().window().maximize();
	}
	
  @Test
  
 public void generationOfReport() {
	  
	  //Excel sheet code to fetch the data from excel like email id and password and pass one by one to the 
	  //UI and test each scenario 
	  
	  //Generating the excel sheet
	  File file=new File("H:\Germany is calling\summary_report.xlsx");
	  FileInputStream fileinput = new FileInputStream(file);
	  
	  //Getting into excelfile and fetching the sheet
	  XSSFWorkbook book = new XSSFWorkbook(fileinput);
	  XSSFSheet sheet = book.getSheet("reports");
	  
	  //selection of the lastrow so the automation tool know from where to start filling the data
	  int lastrow=sheet.getLastRowNum();
	  
	  //using loop to fetch data one by one
	  for(int i=1;i<=lastrow;i++) {
		  //to know for the tester which testcase is running
		  System.out.println("now running : "+ i);
		  
		  // fetching the data from the excel
		  String Emailid = sheet.getrow(i).getcell(1).getStringCellValue();
		  String password = Sheet.getrow(i).getcell(2).getStringCellValue();
		  
		  book.close();
	  }
  
  
  public void Successful_login() {
	  
	  //passing the above fetched values here to the script
	  //Test script for successfull login assuming the user is already signed up
	  
	  
	  driver.findElement(By.id("username")).sendKeys(Emailid);
	  Thread.sleep(200);
	  driver.findElement(By.id("password")).sendKeys(password);
	  Thread.sleep(200);
	  driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[3]/button")).click();
	  
	 //Checking if the user is logged in to the page
	  
	  // using loop to check if the user has landed to the appropriate page if the user is not entered the
	  // appropriate page the loop fails and error message will be printed in console.
	  
	  	WebElement loginpage=driver.findElement(By.xpath("//*[@id=\"menu\"]/li[1]/a"));
	  
	  if loginpage.isDisplayed(){
		 String login_successfull= "login Successful and user is directed to appropriate page";
		 System.out.println("login Successful and user is directed to appropriate page");
	  }
	  else {
		  String login_un-successfull= "login un-Successful and user is not directed to appropriate page";
		  System.out.println("login un-successful user is not directed to the appropriate page");
	  }
	  
	
   public void un_successful_login() {
	  
	  //Test script for un-successfull login assuming the user is already signed up and user is passing 
	  // wrong password.
	  
	  driver.findElement(By.id("username")).sendKeys(Emailid);
	  Thread.sleep(200);
	  driver.findElement(By.id("password")).sendKeys(password);
	  Thread.sleep(200);
	  driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[3]/button")).click();
	  
	  // if the credentials are wrong there is a text message provided on the UI capturing the error message
	  // and telling the user that its un-successfull login
	  
	  //waiting till the error message is showing on UI
	  WebDriverWait wait = new WebDriverWait(driver,60);
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[3]/ul/li"))));
	  
	  //Checking if the error message is displayed
	  WebElement Unsucessfull=driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[3]/ul/li"));
	  
	  //using loop to check 
	  if Unsucessfull.isDisplayed(){
		  String error_msg_displayed = "appropriate error message is displayed to the user";
		  System.out.println("appropriate error message is displayed to the user");
	  }
	  else {
		  String error_msg_not_displayed = "appropriate error message is not displayed to the user";
		  System.out.println("appropriate error message is not displayed to the user");
	  }
	  
	//writing back to the excel sheet the final results
	   
	   File file=new File("H:\Germany is calling\summary_report.xlsx");
	   FileInputStream fileinput = new FileInputStream(file);
	   XSSFWorkbook book = new XSSFWorkbook(fileinput);
	    XSSFSheet sheet = book.getSheet("reports");
	 
	    //selecting the row and cell to write back the data ( login messages)
	    Row row = sheet.getRow(3);
	    if(row==null) {
	    	row = sheet.creatRow(3);
	    }
	    
	    for(j=2;j<=6;j++) {
	    	Cell cell=sheet.getCell(j);
	    	if(cell==null) {
	    		cell = row.createCell(j);
	    	}
	    }
	    
	    Row rows = sheet.getRow(4);
	    if(rows==null) {
	    	rows = sheet.creatRow(4);
	    }
	    
	    for(k=2;k<=6;k++) {
	    	Cell cell=sheet.getCell(k);
	    	if(cell==null) {
	    		cells = rows.createCell(k);
	    	}
	    }
	    
	    cell.setCellValue(login_successfull);
	    cell.setCellValue(login_un-successfull);
	    
	    // to write back the error messages to the excel sheet
	    
	    Row Row = sheet.getRow(5);
	    if(Row==null) {
	    	Row = sheet.creatRow(3);
	    }
	    
	    for(l=2;l<=6;l++) {
	    	Cell Cells=sheet.getCell(l);
	    	if(Cells==null) {
	    		Cells = row.createCell(l);
	    	}
	    }
	    
	    Row Rows = sheet.getRow(4);
	    if(Rows==null) {
	    	Rows = sheet.creatRow(4);
	    }
	    
	    for(m=2;m<=6;m++) {
	    	Cell Cell=sheet.getCell(m);
	    	if(Cell==null) {
	    		Cell = rows.createCell(m);
	    	}
	    }
	    
	    cells.setCellValue(error_msg_displayed );
	    Cells.setCellValue(error_msg_not_displayed);
	    
	    fileOutputStream.close();
	    
	    workbook.close();
  
  }
  }
 
}
