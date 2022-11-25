package transport;

public class Bus extends PublicTransportation {
	private static int BUS_ID = 0;

	private int feeTotal;

	public Bus() {
		vehicleNumber = BUS_ID++;
		numberOfPassengers = 0;
		capacity = 30;
		fuelingAmount = 100;
		velocity = 0;
		status = BusStatus.RUN;
		fee = 1000;
		feeTotal = 0;
	}

	@Override
	public void departure() {
		if (!checkFuel()) {
			return;
		}

		status = BusStatus.RUN;
	}

	@Override
	public void changeVelocity(int velocity) {
		if (!checkFuel()) {
			return;
		}

		this.velocity += velocity;
		if (velocity < 0) {
			velocity = 0;
		}
	}

	@Override
	public void changeState(TransportationStatus status) {
		if (!(status instanceof BusStatus)) {
			System.out.println("\u001B[31m" + "다른 대중교통의 상태 값 입니다." + "\u001B[0m");
			return;
		}

		this.status = status;

		if (status == BusStatus.GARAGE) {
			clearPassenger();
		}
	}

	@Override
	public void boardingPassengers(int passengers) {
		if (status != BusStatus.RUN) {
			System.out.println("\u001B[31m" + "차량이 운행중이지 않습니다." + "\u001B[0m");
			return;
		}

		if (numberOfPassengers + passengers > capacity) {
			System.out.println("\u001B[31m" + "최대 승객 수 초과" + "\u001B[0m");
			clearPassenger();
			return;
		}

		numberOfPassengers += passengers;
		feeTotal += fee * numberOfPassengers;
	}

	@Override
	public boolean isSameNumber(PublicTransportation transportation) {
		if (!transportation.getClass().isInstance(this)) {
			return false;
		}

		return transportation.vehicleNumber == this.vehicleNumber;
	}

	@Override
	public void changeFuel(int amount) {
		fuelingAmount += amount;
		checkFuel();

		if (fuelingAmount < 0) {
			fuelingAmount = 0;
		}
		if (fuelingAmount > 100) {
			fuelingAmount = 100;
		}
	}

	private boolean checkFuel() {
		if (fuelingAmount < 10) {
			System.out.println("\u001B[31m" + "주유 필요" + "\u001B[0m" + "\n");
			changeState(BusStatus.GARAGE);
			return false;
		}

		return true;
	}

	private void clearPassenger() {
		numberOfPassengers = 0;
		feeTotal = 0;
	}

	@Override
	public String toString() {
		return "탑승 승객 수 = " + numberOfPassengers +
			"\n잔여 승객 수 = " + (capacity - numberOfPassengers) +
			"\n주유량 = " + fuelingAmount +
			"\n상태 = " + status +
			"\n요금 확인 = " + feeTotal +
			"\n";
	}
}
