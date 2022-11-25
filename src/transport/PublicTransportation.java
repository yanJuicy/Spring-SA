package transport;

import util.Payable;

public abstract class PublicTransportation implements Payable {
	protected int vehicleNumber;
	protected int fuelingAmount;
	protected int velocity;
	protected int numberOfPassengers;
	protected int capacity;
	protected int fee;
	protected Destination destination;

	protected TransportationStatus status;

	public boolean isSameNumber(PublicTransportation transportation) {
		if (!transportation.getClass().isInstance(this)) {
			return false;
		}

		return transportation.vehicleNumber == this.vehicleNumber;
	}

	public void changeVelocity(int velocity) {
		if (!checkFuel()) {
			return;
		}

		this.velocity = this.velocity + velocity < 0 ? 0 : this.velocity + velocity;
	}

	public void changeFuel(int amount) {
		fuelingAmount = fuelingAmount + amount < 0 ? 0 : fuelingAmount + amount;
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
