
public abstract class Product {
	private String name;
	private int price;

	
	
	public Product(String denumire, int pret) {
		this.name = denumire;
		this.price = pret;
		
	}


	@Override
	public String toString() {
		return  name + ", price=" + price ;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}


	public String getName() {
		
		return this.name;
	}
	
	
	
}
