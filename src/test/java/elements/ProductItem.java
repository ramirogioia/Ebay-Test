package elements;

import java.util.List;

import elements.ProductItem;
import helpers.Printer;

public class ProductItem{
		
		private String nameTitle = "";
		private double price;
		
		
		public ProductItem() {}
		
		public ProductItem(String nameTitle, double value) {
			
			this.nameTitle = nameTitle;
			this.price = value;
		}
			
		public String getNameTitle() {
			return nameTitle;
		}

		public void setNameTitle(String nameTitle) {
			this.nameTitle = nameTitle;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}
		
			
		static public void sortList(List<ProductItem> list, String id, boolean print){
			       
			if(id.equals("price")){
				System.out.println("----SORTED BY PRICE----");
				for(int rounds1=0; rounds1 < list.size()-1; rounds1++){
					for(int rounds2=0; rounds2 < list.size()-1; rounds2++){
						if(list.get(rounds2).getPrice() < list.get(rounds2+1).getPrice()){
							ProductItem aux = list.get(rounds2+1);
							list.set(rounds2+1, list.get(rounds2));
							list.set(rounds2, aux);
						}
					}
				}
			}//Descendant mode for the prices.
			if(id.equals("name")){
				System.out.println("----SORTED BY NAME----");
				list.stream().sorted((object1, object2) -> object1.getNameTitle().compareTo(object2.getNameTitle()));
				}//Ascendant mode for the names of the products.
			if(print){
				Printer.printProductsInformation(list);
			}
		}
	}
