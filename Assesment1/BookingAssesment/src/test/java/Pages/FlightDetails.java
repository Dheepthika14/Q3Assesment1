package Pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Base.TestBase;
import Common.BrowserDriver;
import Utils.DataBaseUtils;

public class FlightDetails {
	WebDriver driver;

	public FlightDetails(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[contains(@class,'Pagination-module__nextArrow')]")
	private static WebElement Nextpage;
	
	public static String NoofFlightsAvailable="//div[@id='flight-card']";
	public static String Paginations="(//button[contains(@class,'Pagination-module__link')])[6]";


	
	public static void getFlightDetails() throws Exception {
		int flightavailable=0;
		int paginationAvail = Integer.valueOf(BrowserDriver.GetAttribute(Paginations, "aria-label").replace(" ", ""));
		TestBase.test.log(Status.INFO,"We have total of "+String.valueOf(paginationAvail)+" pages");
		int count=1;
		for(int i=1;i<=paginationAvail;i++) {
			flightavailable=BrowserDriver.GetSize(NoofFlightsAvailable, "NoofFlightsAvailable");
			for(int j=1;j<=flightavailable;j++) {
				String ID=String.valueOf(count);
				String Flightname = BrowserDriver.GetText("(//div[@data-testid='flight_card_carriers_carrier'])["+j+"]", "Flight name");		
				String Stops = BrowserDriver.GetText("(//div[@data-testid='flight_card_segment_stops'])["+j+"]", "Stops");		
				String Price = BrowserDriver.GetText("(//div[@data-test-id='flight_card_price_main_price']//div)["+j+"]", "Price");		
				String DepartureTime = BrowserDriver.GetText("(//div[@data-testid='flight_card_segment_departure_time'])["+j+"]", "DepartureTime");		
				String DestinationTime = BrowserDriver.GetText("(//div[@data-testid='flight_card_segment_destination_time'])["+j+"]", "DestinationTime");	
				DataBaseUtils.InsertDataintoTable(ID, Flightname, Stops, Price, DepartureTime, DestinationTime);
				count=count+1;
			}
			try {
				BrowserDriver.ClickElement(Nextpage, "Next Page");
				Thread.sleep(5000);
			}catch(Exception e) {}
		}
		TestBase.test.log(Status.PASS,"DataBase updated with the Flight details");
		ArrayList ResultSet = DataBaseUtils.SqlQuery("select * from flightAvailablityDetails where stops='1 stop'");
		EmailConfiguration.SendEmail(ResultSet);
	}
	
	
}
