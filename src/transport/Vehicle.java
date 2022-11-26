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

	public int getVehicleNumber() {
		return vehicleNumber;
	}

	public int getFuelingAmount() {
		return fuelingAmount;
	}

	public int getVelocity() {
		return velocity;
	}

	public void changeVelocity(int velocity) {
		this.velocity = this.velocity + velocity < 0 ? 0 : this.velocity + velocity;
	}

	public void changeFuel(int fuelingAmount) {
		this.fuelingAmount = this.fuelingAmount + fuelingAmount < 0 ? 0 : this.fuelingAmount + fuelingAmount;
	}

	@Override
	public String toString() {
		return "\n차량번호 = " + vehicleNumber +
			"\n주유량 = " + fuelingAmount;
	}
}
