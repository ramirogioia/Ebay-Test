package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helpers.Waiter;
import org.testng.Assert;

public class HomePage {
	
	private WebDriver driver;
	private Waiter waiter;
	private By ebayLogo = By.id("gh-la");
	private By searchField = By.id("gh-ac");
	private By submitButton = By.id("gh-btn");
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		waiter = new Waiter(driver);
	}

	public void checkLoginPage(){
		
		waiter.waitForElement(ebayLogo);
		Assert.assertTrue(driver.findElement(ebayLogo).getAttribute("innerText").equals("eBay"));
		waiter.waitToBeClickable(searchField);
		waiter.waitToBeClickable(submitButton);
	}
	
	public void searchProducts(String information){
		
		driver.findElement(searchField).sendKeys(information);
		driver.findElement(submitButton).click();
	}
	
}
