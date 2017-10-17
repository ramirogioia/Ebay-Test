package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Printer;
import helpers.XMLReader;
import pageObjects.HomePage;
import pageObjects.CustomSearchPage;

public class Tests {
	
	private WebDriver driver;
	private XMLReader reader = new XMLReader();
	
	@BeforeMethod()
	public void setupTest(){
		
		if(reader.readNode("browser").toUpperCase().equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else{
			if(reader.readNode("browser").toUpperCase().equals("FIREFOX")) {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setJavascriptEnabled(true);
				System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else{
				if(reader.readNode("browser").toUpperCase().equals("PHANTOMJS")) {
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setJavascriptEnabled(true);
					caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "Drivers/phantomjs.exe");
					driver = new PhantomJSDriver(caps);
				}
				else{
					driver = null;
				}
			}
		}
		driver.manage().window().maximize();
		driver.navigate().to(reader.readNode("site").toLowerCase()); 
	} 
	 
	
	@Test
	public void customSearchTest() throws InterruptedException{
		HomePage homePage = new HomePage(driver);
		CustomSearchPage searchPage = new CustomSearchPage(driver);
		
		homePage.checkLoginPage();
		homePage.searchProducts(reader.readNode("searchProduct"));
		
		searchPage.checkCustomSearchPage();
		searchPage.applyFilter(reader.readNode("searchType1"), reader.readNode("searchBrand1"));
		Printer.printNumberOfResults(searchPage.numberOfResults(), reader.readNode("searchType1"));
		
		searchPage.checkCustomSearchPage();
		searchPage.checkFilters(reader.readNode("searchBrand1"));
		searchPage.applyFilter(reader.readNode("searchType2"), reader.readNode("searchBrand2"));
		Printer.printNumberOfResults(searchPage.numberOfResults(), reader.readNode("searchType2"));
		
		searchPage.checkCustomSearchPage();
		searchPage.checkFilters(reader.readNode("searchBrand2"));
		searchPage.sortListBy(reader.readNode("sortBy"), reader.readNode("typeSort"));
		
		searchPage.checkCustomSearchPage();
		searchPage.obtainFirstNResults(reader.readNode("nResults"), true);
		searchPage.sortAndPrintList(reader.readNode("organiceList1"));
		searchPage.sortAndPrintList(reader.readNode("organiceList2"));
	}
	
	@AfterMethod()
	public void tearDown(){
		
		driver.close();
		driver.quit();
	}

}
