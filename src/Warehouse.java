import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.AbstractListModel;

	// abstractlist for usage with JList
public class Warehouse extends AbstractListModel<Product> {

	private static final long serialVersionUID = 1L;
	private String name;
	// product list
	private ArrayList<Product> list;
	
	
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Product> getWarehouseList (){
		return list;
	}
	
	
	// constructor
	Warehouse(String name){
		this.name = name;
		 list = new ArrayList<Product>();
	}
	
	// this method is not used at the moment
	// will be implemented in the future for stock counting
	public ArrayList<String> returnWarehouse(){
		if(list.isEmpty()){
			System.out.println("Warehouse is Empty.");
			return null;
		}
		ArrayList<String> forJList = new ArrayList<String>();
		HashSet<Product> listShow = new HashSet<Product>(list);
			for(Product product: listShow){
				int count=0;
				for(Product product2: list){
					if(product.equals(product2)){
						count++;
					}
				}
				forJList.add(product.getName() +" stock "+  count);
			}
			return forJList;
		}
	
	// this method is not used at the moment
	// will be implemented in the future for stock counting
	public void showWarehouse(){
		if(list.isEmpty()){
			return;
		}
		
		HashSet<Product> listShow = new HashSet<Product>(list);
			for(Product product: listShow){
				int count=0;
				for(Product product2: list){
					if(product.equals(product2)){
						count++;
					}
				}
				System.out.println(product +" stock "+  count + " - Warehouse "+ this.name);
			}
		}
	
	// adds product to warehouse list
	
	public void  addProductToWarehouse(Product product){
			list.add(product);
			this.fireContentsChanged(this, -1, -1);
	}
	
	// removes product from warehouse and returns it
	public Product removeProductFromWarehouse(Product product){
		
		if(product != null){
			for(Product prod: list){
				if(prod.equals(product)){
					list.remove(prod);
					this.fireContentsChanged(this, -1, -1);
					
					return prod;
				}
			}
			
		
		
	}	
		return null;
		
	}

	// method for JList
	@Override
	public Product getElementAt(int index) {
		return list.get(index);
	}

	// method for JList
	@Override
	public int getSize() {
		return list.size();
	}

}