package model;

import java.util.Optional;

public class Warehouse {

	private final int shelfID;
	private final int laneID;
	private final int compartmentID;
	private final Optional<Integer> modelID;

	public Warehouse(final int shelfID, final int laneID, final int cmpartmentID, Optional<Integer> modelID) {
		this.shelfID = shelfID;
		this.laneID = laneID;
		this.compartmentID = cmpartmentID;
		this.modelID = modelID;
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

	public Optional<Integer> getModelID() {
		return modelID;
	}

}
