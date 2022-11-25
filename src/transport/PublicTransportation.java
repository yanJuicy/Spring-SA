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

	public abstract void departure();

	public abstract void changeVelocity(int velocity);

	public abstract void changeState(TransportationStatus status);

	public abstract void boardingPassengers(int passengers);
	public abstract void changeFuel(int amount);
}
