package transport;

public class Bus extends PublicTransportation {
	private static int BUS_ID = 0;
	private static int FUEL_LOWER_LIMIT = 10;

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
			System.out.println("\u001B[31m" + "차량이 운행중이지 않습니다." + "\u001B[0m" + "\n");
			return;
		}

		if (numberOfPassengers + passengers > capacity) {
			System.out.println("\u001B[31m" + "최대 승객 수 초과" + "\u001B[0m" + "\n");
			clearPassenger();
			return;
		}

		numberOfPassengers += passengers;
		feeTotal += fee * numberOfPassengers;
	}

	@Override
	protected boolean checkFuel() {
		if (fuelingAmount < FUEL_LOWER_LIMIT) {
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
