package transport;

public abstract class PublicTransportation {
	protected int vehicleNumber;
	protected int fuelingAmount;
	protected int velocity;
	protected int numberOfPassengers;
	protected int capacity;
	protected int fee;
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

		this.velocity += velocity;
		if (velocity < 0) {
			velocity = 0;
		}
	}

	public void changeFuel(int amount) {
		fuelingAmount += amount;
		checkFuel();

		if (fuelingAmount < 0) {
			fuelingAmount = 0;
		}
	}

	public abstract void departure();

	public abstract void changeState(TransportationStatus status);

	public abstract void boardingPassengers(int passengers);

	protected abstract boolean checkFuel();
}
