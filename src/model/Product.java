package model;

public class Product {

	private final int prodCode;
	private final String modelID;

	public Product(final int prodCode, final String modelID) {
		this.prodCode = prodCode;
		this.modelID = modelID;
	}

	public int getProdCode() {
		return prodCode;
	}

	public String getModelID() {
		return modelID;
	}

}
