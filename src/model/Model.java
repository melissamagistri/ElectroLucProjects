package model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Optional;

public class Model {

	private final int modelID;
	private final String modelName;
	private final String brand;
	private final String description;
	private final String category;
	private final Optional<Blob> imageUri;
	private final BigDecimal unitPrice;
	private final Optional<Integer> discount;
	private final int unitInStock;
	private final int maxUnitPerOrder;
	private final String shelf;
	private final String lane;
	private final String compartment;
	private final boolean salesCatalog;

	public Model(final int modelID, final String modelName, final String brand, final String description,
			final Optional<Blob> imageUrl,final BigDecimal price, final Optional<Integer> discount, 
			final int unitInStock, final int maxUnitPerOrder, final String modelCategory, final String magazineShelf,
			final String magazineLane, final String magazineCompartment, final boolean salesCatalog) {
		this.modelID = modelID;
		this.modelName = modelName;
		this.brand = brand;
		this.description = description;
		this.imageUri = imageUrl;
		this.unitPrice = price;
		this.discount = discount;
		this.unitInStock = unitInStock;
		this.maxUnitPerOrder = maxUnitPerOrder;
		this.category = modelCategory;
		this.shelf = magazineShelf;
		this.lane = magazineLane;
		this.compartment = magazineCompartment;
		this.salesCatalog = salesCatalog;
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

	public Optional<Blob> getImageUri() {
		return imageUri;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public String getCategory() {
		return category;
	}

	public int getMaxUnitPerOrder() {
		return maxUnitPerOrder;
	}

	public String getShelf() {
		return shelf;
	}

	public String getLane() {
		return lane;
	}

	public String getCompartment() {
		return compartment;
	}

	public boolean isInSalesCatalog() {
		return salesCatalog;
	}

}
