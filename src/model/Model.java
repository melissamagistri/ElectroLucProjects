package model;

import java.util.Optional;

public class Model {

	private final int modelID;
	private final String modelName;
	private final String brand;
	private final String description;
	private final String category;
	private final String imageUri;
	private final int unitPrice;
	private final Optional<Integer> discount;
	private final int unitInStock;
	private final int maxUnitPerOrder;
	private final int shelf;
	private final int lane;
	private final int compartment;
	private final boolean salesCatalog;

	private Model(final int modelID, final String modelName, final String brand, final String description,
			final String imageUrl,final int price, final Optional<Integer> discount, 
			final int unitInStock, final int maxUnitPerOrder, final String modelCategory, final int magazineShelf,
			final int magazineLane, final int magazineCompartment, final boolean salesCatalog) {
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

	public Model(final int modelID, final String brand, final String modelName, final String description,
			final String imageUrl,final int price, final int unitInStock, final int maxUnitPerOrder, 
			final String modelCategory, final int magazineShelf,
			final int magazineLane, final int magazineCompartment, final boolean salesCatalog) {
		this(modelID, modelName, brand, description, imageUrl, price, Optional.empty(), 
				unitInStock, maxUnitPerOrder, modelCategory, magazineShelf, magazineLane, magazineCompartment, 
				salesCatalog);
	}

	public Model(final int modelID, final String brand, final String modelName, final String description,
	final String imageUrl,final int price, final int discount, 
	final int unitInStock, final int maxUnitPerOrder, final String modelCategory, final int magazineShelf,
	final int magazineLane, final int magazineCompartment, final boolean salesCatalog) {
		this(modelID, modelName, brand, description, imageUrl, price, Optional.of(discount), 
				unitInStock, maxUnitPerOrder, modelCategory, magazineShelf, magazineLane, magazineCompartment,
				salesCatalog);
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

	public String getImageUri() {
		return imageUri;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public String getCategory() {
		return category;
	}

	public int getMaxUnitPerOrder() {
		return maxUnitPerOrder;
	}

	public int getShelf() {
		return shelf;
	}

	public int getLane() {
		return lane;
	}

	public int getCompartment() {
		return compartment;
	}

	public boolean isInSalesCatalog() {
		return salesCatalog;
	}

}
