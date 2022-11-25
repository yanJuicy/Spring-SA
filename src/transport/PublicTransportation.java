package transport;

import util.Payable;

public abstract class PublicTransportation implements Payable {

	protected Vehicle vehicle;
	protected int numberOfPassengers;
	protected int capacity;
	protected int fee;
	protected Destination destination;

	protected TransportationStatus status;

	public boolean isSameNumber(PublicTransportation transportation) {
		if (!transportation.getClass().isInstance(this)) {
			return false;
		}

		return transportation.vehicle.getVehicleNumber() == this.vehicle.getVehicleNumber();
	}

	public void changeVelocity(int velocity) {
		if (!checkFuel()) {
			return;
		}

		vehicle.setVelocity(vehicle.getVelocity() + velocity < 0 ? 0 : vehicle.getVelocity() + velocity);
	}

	public void changeFuel(int amount) {
		vehicle.setFuelingAmount(vehicle.getFuelingAmount() + amount < 0 ? 0 : vehicle.getFuelingAmount() + amount);
		checkFuel();
	}

	public void changeDestination(String name, int distance) {
		this.destination = new Destination(name, distance);
	}

	public abstract void departure();

	public abstract void changeState(TransportationStatus status);

	public abstract void boardingPassengers(int passengers);

	protected abstract boolean checkFuel();
}
