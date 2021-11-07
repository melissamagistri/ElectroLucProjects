package model;

import java.math.BigDecimal;
import java.util.Optional;

public class Model {

	private final int modelID;
	private final String modelName;
	private final String brand;
	private final String description;
	private final String category;
	private final BigDecimal unitPrice;
	private final Optional<Integer> discount;
	private final int unitInStock;
	private final boolean inSale;

	public Model(final int modelID, final String modelName, final String brand, final String description,
			final BigDecimal price, final Optional<Integer> discount, 
			final int unitInStock, final String modelCategory, final boolean inSale) {
		this.modelID = modelID;
		this.modelName = modelName;
		this.brand = brand;
		this.description = description;
		this.unitPrice = price;
		this.discount = discount;
		this.unitInStock = unitInStock;
		this.category = modelCategory;
		this.inSale = inSale;
	}

	public Optional<Integer> getDiscount() {
		return discount;
	}

	public int getUnitInStock() {
		return unitInStock;
	}

	public int getModelID() {
		return modelID;
	}

	public String getModelName() {
		return modelName;
	}

	public String getBrand() {
		return brand;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public String getCategory() {
		return category;
	}

	public boolean isInSales() {
		return inSale;
	}

}
