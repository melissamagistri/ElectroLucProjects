package model;

import java.math.BigDecimal;

public class AvaibilityTable {
	
	private final String lane;
	private final String shelf;
	private final String compartment;
	private final String name;
	private final int unitInStock;
	private final BigDecimal unitSellingPrice;
	private final Boolean state;
	
	public AvaibilityTable(String name, BigDecimal unitSellingPrice, 
			int unitInStock, Boolean state, String lane, String shelf, String compartment ) {
		this.lane = lane;
		this.shelf = shelf;
		this.compartment = compartment;
		this.name = name;
		this.unitInStock = unitInStock;
		this.unitSellingPrice = unitSellingPrice;
		this.state = state;
	}
	
	public String getLane() {
		return lane;
	}
	public String getShelf() {
		return shelf;
	}
	public String getCompartment() {
		return compartment;
	}
	public String getName() {
		return name;
	}
	public int getUnitInStock() {
		return unitInStock;
	}
	public BigDecimal getUnitSellingPrice() {
		return unitSellingPrice;
	}
	public Boolean getState() {
		return state;
	}
	
}
