package model;

import java.math.BigDecimal;
import java.util.Optional;

public class Model {

	private final int modelID;
	private final String modelName;
	private final String brand;
	private final String description;
	private final String category;
	private final BigDecimal unitSellingPrice;
	private final BigDecimal unitPurchasePrice;
	private final int discount;
	private final int unitInStock;
	private final boolean inSale;

	public Model(final int modelID, final String modelName, final String brand, final String description,
			final BigDecimal unitSellingPrice, final BigDecimal unitPurchasePrice, final Optional<Integer> discount, 
			final int unitInStock, final String modelCategory, final boolean inSale) {
		this.modelID = modelID;
		this.modelName = modelName;
		this.brand = brand;
		this.description = description;
		this.unitSellingPrice = unitSellingPrice;
		this.unitPurchasePrice = unitPurchasePrice;
		this.discount = discount.isEmpty() ? 0 : discount.get();
		this.unitInStock = unitInStock;
		this.category = modelCategory;
		this.inSale = inSale;
	}

	public int getDiscount() {
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

	public BigDecimal getUnitSellingPrice() {
		return unitSellingPrice;
	}

	public BigDecimal getUnitPurchasePrice() {
		return unitPurchasePrice;
	}

	public String getCategory() {
		return category;
	}

	public boolean isInSales() {
		return inSale;
	}

}
