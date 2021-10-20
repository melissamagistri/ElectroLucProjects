package model;

public class Warehouse {

	private final int shelfID;
	private final int laneID;
	private final int compartmentID;

	public Warehouse(final int shelfID, final int laneID, final int cmpartmentID) {
		this.shelfID = shelfID;
		this.laneID = laneID;
		this.compartmentID = cmpartmentID;
	}

	public int getShelfID() {
		return shelfID;
	}

	public int getLaneID() {
		return laneID;
	}

	public int getCompartmentID() {
		return compartmentID;
	}

}
