package Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Base.TestBase;

public class BrowserDriver {
	static WebDriver driver =TestBase.driver;

	public BrowserDriver(WebDriver driver) {
		this.driver = TestBase.driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public static void SendTextBox(WebElement element, String InputText) {
		element.sendKeys(InputText);
	}
	
	public static void ClickElement(WebElement element, String ElementName) {		
		element.click();
	}
	
	public static void StringClickElement(String element, String ElementName) {	
		try {
			driver.findElement(By.xpath(element)).click();
			TestBase.test.log(Status.PASS,"The element is clickable : "+ElementName);
		}catch(Exception e) {
			TestBase.test.log(Status.FAIL,"The element is not clickable : "+ElementName);
		}
	}
	
	public static void JSScriptClick(WebElement element, String ElementName) {		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("element.click();");
	}
	public static int GetSize(String element, String ElementName) {		
		int TheSize =driver.findElements(By.xpath(element)).size();
		return TheSize;
	}
	
	public static String GetText(String element, String ElementName) {		
		String TheText =driver.findElement(By.xpath(element)).getText();
		return TheText;
	}
	
	public static String GetAttribute(String element, String Attribute) {		
		String TheAttributeText =driver.findElement(By.xpath(element)).getAttribute(Attribute);
		return TheAttributeText;
	}
	public static void IsClickable(WebElement element, String ElementName) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			wt.until(ExpectedConditions.elementToBeClickable (element));
		}catch(Exception e) {
			TestBase.test.log(Status.FAIL, ElementName + " is not clickable");
		}
	}
	
}
