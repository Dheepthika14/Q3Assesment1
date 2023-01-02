package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Base.TestBase;
import Common.BrowserDriver;
import Utils.DataBaseUtils;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@data-decider-header='flights']")
	private static WebElement FlightTab;

	@FindBy(xpath="//input[@value='ONEWAY']")
	private static WebElement OneWayOption;

	@FindBy(xpath="(//div[contains(@class,'inputContainer')])[1]")
	private static WebElement FromTextBox;

	@FindBy(xpath="//div[contains(@class,'autocompleteInput')]//input")
	private static WebElement FromTextBoxText;

	@FindBy(xpath="//div[contains(@data-testid,'autocomplete_result')]")
	private static WebElement AutoCompleteDropdown;

	@FindBy(xpath="//input[contains(@data-testid,'searchbox_destination_input')]")
	private static WebElement ToTextBox;

	@FindBy(xpath="//button[contains(@data-testid,'searchbox_submit')]")
	private static WebElement SearchButton;

	@FindBy(xpath="//div[contains(@class,'autocompleteInput')]//span[contains(@class,'Icon-module__root___DweoM Icon-module__root')]")
	private static WebElement PreviousSearchClose;
	

	public static void HomePageTest() throws Exception {
		TestBase.test.log(Status.INFO, "**********Home Page*********");
		Thread.sleep(1000);
		BrowserDriver.ClickElement(FlightTab, "FlightTab");
		TestBase.test.log(Status.PASS,"Switched to flight tab");
		Thread.sleep(1000);
		BrowserDriver.ClickElement(OneWayOption, "OneWay Option");
		BrowserDriver.ClickElement(FromTextBox, "FromTextBox");
		try {
			BrowserDriver.ClickElement(PreviousSearchClose, "PreviousSearchClose");
		}catch(Exception e) {}
		BrowserDriver.SendTextBox(FromTextBoxText, "CHENNAI");
		TestBase.test.log(Status.PASS,"From place selected");
		Thread.sleep(1000);
		BrowserDriver.ClickElement(AutoCompleteDropdown, "From Dropdown");
		Thread.sleep(1000);
		BrowserDriver.SendTextBox(ToTextBox, "DELHI");
		TestBase.test.log(Status.PASS,"Destination place selected");
		Thread.sleep(1000);
		BrowserDriver.ClickElement(AutoCompleteDropdown, "To Dropdown");
		Thread.sleep(1000);
		BrowserDriver.ClickElement(SearchButton, "Search Button");
		Thread.sleep(6000);

	}

}
