package helpers;

import java.util.List;
import elements.ProductItem;

public class Printer {
	
	
	private static void printProductInformation(ProductItem element){

		System.out.println("Product: " + element.getNameTitle());
		System.out.println("Price: USD $" + element.getPrice());	
	}
	
	public static void printProductsInformation(List<ProductItem> elements){
		for(int i=0; i<elements.size(); i++){
			printProductInformation(elements.get(i));
		}
	}
	
	public static void printNumberOfResults(String nResults, String searchType){
		
		System.out.println("You have: " + nResults.replace(" ", ".") + " results for the search by: " + searchType);
	}
}
