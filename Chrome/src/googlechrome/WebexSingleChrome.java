package googlechrome;

import static org.testng.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class WebexSingleChrome {
	
	private WebDriver driver;
	  private String baseUrl;
	  public static Logger logger = Logger.getLogger(SingleMeetingChrome.class);
	  
	  
	  
@BeforeClass(alwaysRun = true)
public void setUp() throws Exception {
  	PropertyConfigurator.configure("log4j.properties");
  	System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe"); 
      driver = new ChromeDriver();  
      Thread.sleep(4000);
   
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
}



@Test
public void open() throws Exception {
	
	
	  driver.get("https://administrator:cisco@123@10.77.197.240/tmsagent/tmsportal/");
	  Thread.sleep(10000);
	  
	  WebElement ele = driver.findElement(By.xpath("/html/body/div[1]/div/ul/li[2]/a/span"));
	  String str = ele.getText();
	  System.out.println(str);
			  
	  String atitle = "TelePresence User Portal: 10.77.197.240";
	  String btitle = "TelePresence User Portal";
	  String ctitle = "The TMS User Portal is currently unavailable. Please try again later."; 
	
	  //get page title and store in btitle
	  String dtitle = driver.getTitle();	
	  System.out.println(dtitle);
	  
	  WebElement element = driver.findElement(By.xpath("html/body/div"));
	  String strng = element.getText();	
 
	  if (dtitle.equals(atitle))
	  {	  	  
       create();    
       edit();
       delete();
    }	  
	  else if (dtitle.equals(btitle))
	  {
		  create();    
	      edit();
	      delete();
	  }
	  else if (ctitle.equals(strng))
	  {
		 logger.error("Server is restarting");
		 org.testng.Assert.fail("Fail");
		 driver.quit();
	  }
	  else  
    {	  
	     logger.error("Server not accessible");
	     org.testng.Assert.fail("Fail");
	     driver.quit();
    }	  
	

}

  
  public void create() throws InterruptedException {
  try{
	
	  //click on open smart scheduler
	  driver.findElement(By.linkText("Open Smart Scheduler")).click();
      Thread.sleep(1000);
      //click on new meeting
      driver.findElement(By.xpath("//div[3]/div/div/div/div/div[1]/div[1]/h2/button")).click();
	    Thread.sleep(1000);
	    
	  //select participants
      driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/input")).click();
      driver.findElement(By.name("search")).click();
      driver.findElement(By.name("search")).clear();
	  driver.findElement(By.name("search")).sendKeys("a");
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div/div/div/div[2]/div/a/span[2]")).click();
	  Thread.sleep(1000);
	  
	  
	  
	  //select dial-in participant
	  driver.findElement(By.xpath(" //*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[1]/a")).click();  
      
	  
	  
	  
	  //add webex participant	
	  
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div[2]/div/div/ul/li[2]/a")).click();
	  driver.findElement(By.xpath("//*[@id='SchedEdit-WebExCollapse']/div/div/div[2]/div[2]/div[1]/label")).click();
      
      
	  //change title name
	  driver.findElement(By.xpath("//*[@id='title']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='title']")).clear();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='title']")).sendKeys("single meeting");
	  
	  
	  
	  //select start time
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/input")).click();
      driver.findElement(By.xpath("html/body/ul/li[81]")).click();
	  
	  //click save button
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/button[1]")).click();    
	  logger.info("Meeting scheduled successfully");
	  
    }
    catch (Exception e1){
	     logger.error("Meeting not created");
	     org.testng.Assert.fail("Fail");
 	     driver.close();
    }
  
	  //back to home page
	  driver.navigate().back();
	  Thread.sleep(1000);
    
  
  }
  
 
	  
	  
  
  public void edit() throws InterruptedException {
  try{
	
	  driver.findElement(By.linkText("Open Smart Scheduler")).click();
      Thread.sleep(1000);
      
      //click on new meeting
      driver.findElement(By.xpath("html/body/div[3]/div/div/div/div/div[1]/div[2]/div/div[2]/div/div/a/div[2]/div")).click();
	  Thread.sleep(1000);
	    
		  
	  //select dial-in participant
	  driver.findElement(By.xpath(" //*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[2]/a")).click();  
      
	  
	  
	  //add webex participant	  
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div[2]/div/div/ul/li[2]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='webex_password']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='webex_password']")).sendKeys("12345abc");
	  Thread.sleep(1000);
	  
	  
	  
	  //change title name
	  driver.findElement(By.xpath("//*[@id='title']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='title']")).clear();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='title']")).sendKeys("webex meeting");
	  Thread.sleep(1000);
	  
	  

	  //click save button
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/button[1]")).click();    
	  Thread.sleep(1000);
	  logger.info("Meeting edited successfully");
	
     }
     catch (Exception e2){
	       logger.error("Meeting not edited");
	       org.testng.Assert.fail("Fail");
	 	   driver.quit();
     }
	  
	  //back to home page
	  driver.navigate().back();
	  Thread.sleep(1000);
    
 
  }
  
  
  
public void delete() throws Exception {
	try{

	      
	    driver.findElement(By.linkText("Open Smart Scheduler")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("html/body/div[3]/div/div/div/div/div[1]/div[2]/div/div[2]/div/div/a/div[2]/div")).click();
	    
	    driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/button[3]")).click();
	    
	    driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/div/div[2]/div/div/div[1]/button")).click();
	    
	    logger.info("Meeting deleted successfully");
	  
	   }
	   catch (Exception e3){
		   logger.error("Meeting not deleted");
		   org.testng.Assert.fail("Fail");
	 	   driver.quit();
	   }
	 
	    //back to home page
		driver.navigate().back();
		Thread.sleep(1000);
	
	
  } 
  


  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	  Thread.sleep(5000);
      driver.quit();
     
  }
 
}
