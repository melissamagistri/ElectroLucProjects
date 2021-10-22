package model;

public class Product {

	private final int prodID;
	private final String modelID;

	public Product(final int productID, final String modelID) {
		this.prodID = productID;
		this.modelID = modelID;
	}

	public int getProductID() {
		return prodID;
	}

	public String getModelID() {
		return modelID;
	}

}
