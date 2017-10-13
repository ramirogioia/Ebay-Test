package helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
	
	private WebDriver driver;
	private WebElement anElement;
	
	
	public Waiter(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void waitToBeClickable(By element) {
	
		anElement = (new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(element));
	} 
	
	
	public void waitToBeClickable(WebElement element) {
		
		anElement = (new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(element));
	} 
	
	
	public void waitForElement(By element) {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(element);
	}
	
	
	public void waitForElement(WebElement element) {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		element.isDisplayed();
	}
	
	
	public boolean isClickable(WebElement element, WebDriver driver){
		
        try{
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
