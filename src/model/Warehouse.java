package model;

import java.util.Optional;

public class Warehouse {

	private final String shelfID;
	private final String laneID;
	private final String compartmentID;
	private final Optional<Integer> modelID;

	public Warehouse(final String shelfID, final String laneID, final String cmpartmentID, Optional<Integer> modelID) {
		this.shelfID = shelfID;
		this.laneID = laneID;
		this.compartmentID = cmpartmentID;
		this.modelID = modelID;
	}

	public String getShelfID() {
		return shelfID;
	}

	public String getLaneID() {
		return laneID;
	}

	public String getCompartmentID() {
		return compartmentID;
	}

	public Optional<Integer> getModelID() {
		return modelID;
	}

}
