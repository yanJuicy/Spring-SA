package transport;

public class Bus extends PublicTransportation {
	private static int BUS_ID = 0;

	public Bus() {
		vehicleNumber = BUS_ID++;
		numberOfPassengers = 0;
		capacity = 30;
		fuelingAmount = 100;
		velocity = 0;
		status = BusStatus.RUN;
		fee = 1000;
	}

	@Override
	public void departure() {
		if (fuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = BusStatus.GARAGE;
			return;
		}
	}

	@Override
	public void changeVelocity(int velocity) {
		if (fuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = BusStatus.GARAGE;
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
			System.out.println("다른 대중교통의 상태 값 입니다.");
			return;
		}

		this.status = status;
	}

	@Override
	public void boardingPassengers(int passengers) {
		if (status != BusStatus.RUN) {
			System.out.println("차량이 운행중이지 않습니다.");
			return;
		}

		if (numberOfPassengers + passengers > capacity) {
			System.out.println("탑승 인원을 초과했습니다.");
			return;
		}

		numberOfPassengers += passengers;
		fee *= numberOfPassengers;
	}

	@Override
	public boolean isSameNumber(PublicTransportation transportation) {
		if (!transportation.getClass().isInstance(this)) {
			return false;
		}

		return transportation.vehicleNumber == this.vehicleNumber;
	}

	@Override
	public void fuelAdd(int amount) {
		fuelingAmount += amount;
		if (fuelingAmount < 0) {
			fuelingAmount = 0;
		}
		if (fuelingAmount > 100) {
			fuelingAmount = 100;
		}
	}

	@Override
	public String toString() {
		return "탑승 승객 수 = " + numberOfPassengers +
			"\n잔여 승객 수 = " + (capacity - numberOfPassengers) +
			"\n주유량 = " + fuelingAmount +
			"\n상태 = " + status +
			"\n요금 확인 = " + fee +
			"\n";
	}
}
