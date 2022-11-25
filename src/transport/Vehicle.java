package transport;

public class Vehicle {

	private int vehicleNumber;
	private int fuelingAmount;
	private int velocity;

	public Vehicle(int vehicleNumber, int fuelingAmount, int velocity) {
		this.vehicleNumber = vehicleNumber;
		this.fuelingAmount = fuelingAmount;
		this.velocity = velocity;
	}

	@Override
	public String toString() {
		return "\n차량번호 = " + vehicleNumber +
			"\n주유량 = " + fuelingAmount;
	}

	public int getVehicleNumber() {
		return vehicleNumber;
	}

	public int getFuelingAmount() {
		return fuelingAmount;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public void setFuelingAmount(int fuelingAmount) {
		this.fuelingAmount = fuelingAmount;
	}
}
