package transport;

import java.util.UUID;

public abstract class PublicTransportation {
	protected UUID vehicleNumber;
	protected int refuelingAmount;
	protected int numberOfPassengers;
	protected int capacity;

	protected int velocity;
	protected int fee;
	protected TransportationStatus status;

	public abstract void departure();
	public abstract void changeVelocity(int velocity);
	public abstract void changeState(TransportationStatus status);
	public abstract void boardingPassengers(int passengers);

}
