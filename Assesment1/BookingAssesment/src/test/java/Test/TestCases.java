package Test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Common.BrowserDriver;
import Pages.FlightDetails;
import Pages.HomePage;
import Utils.DataBaseUtils;


public class TestCases extends TestBase {
	
	WebDriver driver;
	private HomePage HomePageValid;
	private FlightDetails FlightDetailsValid;
	DataBaseUtils utils = new DataBaseUtils();
	
	public TestCases() {
		try {
			utils.createConnection();
			utils.CreateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void HomePage() throws Exception {
		HomePageValid = new HomePage(TestBase.driver);
		HomePageValid.HomePageTest();	
		FlightDetailsValid = new FlightDetails(TestBase.driver);
		FlightDetailsValid.getFlightDetails();
		
	}
}
