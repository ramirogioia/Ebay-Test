package pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import elements.ProductItem;
import helpers.Printer;
import helpers.Waiter;

public class CustomSearchPage {
	
	private WebDriver driver;
	private Waiter waiter;
	private Actions action;
	private List<ProductItem> products;
	
	private By ebayLogo = By.id("gh-la");
	private By subtitle = By.id("gh-shop-a");
	private By filterPanel = By.id("e1-18");
	private By nResults = By.className("rcnt");
	private By filtersTab = By.id("smuys");
	private By filter = By.className("cons ");
	private By sortMenu = By.id("DashSortByContainer");
	private By productContainer = By.cssSelector(".sresult.gvresult");
	private By tittle = By.className("gvtitle");
	private By price = By.className("gvprices");
	private By tittleTagName = By.tagName("a");
	private By priceSelector = By.cssSelector(".bold");
	
	
	public CustomSearchPage(WebDriver driver) {
		
		this.driver = driver;
		waiter = new Waiter(driver);
		action = new Actions(driver);
		
	}
	
	public void checkCustomSearchPage(){
		
		waiter.waitForElement(ebayLogo);
		Assert.assertTrue(driver.findElement(ebayLogo).getAttribute("innerText").equals("eBay"));
		waiter.waitForElement(subtitle);
		Assert.assertTrue(driver.findElement(subtitle).getText().contains("categoría"));
		waiter.waitForElement(sortMenu);
	}
	
	public void checkFilters(String verificator){
		waiter.waitForElement(filtersTab);
		boolean haveIt = false;
		List<WebElement> listOfFilters = driver.findElement(filtersTab).findElements(filter);
		for (WebElement element : listOfFilters) {
			waiter.waitForElement(element);
			if(element.findElement(By.tagName("b")).getAttribute("data-value").equals(verificator)){
				haveIt = true;
			}
		}
		Assert.assertTrue(haveIt);
	}
	
	
	private WebElement foundFilter(List<WebElement> list, String atributte, String condition){
		for(int i = 0; i<list.size(); i++){
			if (list.get(i).getAttribute(atributte).contains(condition) && waiter.isClickable(list.get(i), driver)){
				return list.get(i);
			}
		}
		return null;
	}
	
	public void applyFilter(String searchType, String searchBrand){
		
		waiter.waitForElement(filterPanel);
		List<WebElement> listOfPanels = driver.findElement(filterPanel).findElements(By.className("pnl"));
		String attributeToSearch = "innerText";
		WebElement checkBox = (foundFilter(foundFilter(listOfPanels, attributeToSearch, searchType).findElements(By.className("cbx")), attributeToSearch, searchBrand)).findElement(By.tagName("input"));
		try {
	        waiter.waitToBeClickable(checkBox);
	        checkBox.click();
	    }
	    catch (WebDriverException wde) {
	        scrollToElement(checkBox);
	        checkBox.click();
	    }
	}
	
	public void sortListBy(String sortBy, String typeSort){
		
		WebElement orderBy = driver.findElement(sortMenu);
		action.moveToElement(orderBy).click().perform();
		List<WebElement> options = orderBy.findElements(By.tagName("li"));
		boolean verificator = false;
		for(int i=0; i<options.size(); i++){
			if(options.get(i).findElement(By.tagName("a")).getAttribute("innerHTML").startsWith(sortBy) && options.get(i).getAttribute("innerHTML").contains(typeSort)){
				options.get(i).click();
				verificator = true;
				break;
			}
		}
		Assert.assertTrue(verificator);
	}
	
	public void obtainFirstNResults(String quantity, boolean print){
		int intQuantity = Integer.valueOf(quantity);
		waiter.waitForElement(productContainer);
		List<WebElement> productsList = driver.findElements(productContainer).subList(0, intQuantity);
		products = new ArrayList<ProductItem>(intQuantity);
		//double aux = 0;
		for(int i=0; i<intQuantity; i++){
			String name = productsList.get(i).findElement(tittle).findElement(tittleTagName).getText();
			double value = Double.parseDouble(productsList.get(i).findElement(price).findElement(priceSelector).getText().substring(3, 7));
			//Assert.assertTrue(aux <= value); //Here I assert the correct order BYPRICE directly // is Comented because in some particular cases the search brings confused prices. But have to be like this.
			//aux = value;
			ProductItem product = new ProductItem(name, value);
			products.add(product);
		}
		if(print){
			Printer.printProductsInformation(products);
		}
	}
	
	public void sortAndPrintList(String id){
		ProductItem.sortList(products, id, true);
	}
	
	public String numbreOfResults(){
		return driver.findElement(nResults).getText();
	}
	
	
	private void scrollToElement(WebElement element) {
	    if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    }
	}
}
