package Base;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TestBase {
	public static WebDriver driver;
	public static String ProjectPath = System.getProperty("user.dir");
	static Properties configProp = new Properties();
	public static WebDriverWait wait;
	ExtentSparkReporter reports=null;
	ExtentReports extent=null;
	public static ExtentTest test=null;
	
	@Parameters("browserName")	
	@BeforeSuite
	public void BrowserInitialisation(@Optional("chrome") String browserName) throws Exception{		
		try {
			reports=new ExtentSparkReporter("ExecutionResults.html");
			extent = new ExtentReports();
			extent.attachReporter(reports);
			test = extent.createTest("Booking login");
		}catch(Exception e) {
			System.out.println("Exception in block : "+e);
		}
		FileInputStream input = new FileInputStream(ProjectPath+"/src/test/java/Config/config.properties");
		configProp.load(input);		
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}else {
				driver = new ChromeDriver();
			}
			wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().deleteAllCookies();
			test.log(Status.INFO, "Browser opened successfully");
		} catch(Exception e) {
			
		}
		
	}
	
	@BeforeMethod
	public void getUrl(Method method) {		
		driver.get(configProp.getProperty("url"));
	}
	
	@AfterMethod
	public void screenshotandCookies(ITestResult testresult) throws Exception {
		if(testresult.getStatus()==ITestResult.FAILURE) {
			File ScreenshotFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(ScreenshotFile, new File(ProjectPath+"/FailedScreenshot/"+testresult.getName()+"_"+Arrays.toString(testresult.getParameters())+".jpg"));
			RetryFailedTestCases retryFailCls = new RetryFailedTestCases();
			retryFailCls.retry(testresult);
		}
		driver.manage().deleteAllCookies();
	}
	
	public class RetryFailedTestCases implements IRetryAnalyzer {
	    private int retryCnt = 0;	    
	    private int maxRetryCnt = 3;	    	    
	    public boolean retry(ITestResult result) {
	        if (retryCnt < maxRetryCnt) {
	            System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCnt+1));
	            result.getClass();
	            retryCnt++;	           
	        }
	        return false;
	    }
	  
	}	
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
		driver.quit();
	}


}
