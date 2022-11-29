package transport.bus;

import transport.PublicTransportation;
import transport.TransportationStatus;
import transport.Vehicle;
import util.Utils;

public class Bus extends PublicTransportation {
	private static final int FUEL_LOWER_LIMIT = 10;
	private static int BUS_ID = 0;

	public Bus() {
		vehicle = new Vehicle(BUS_ID++, 100, 0);
		numberOfPassengers = 0;
		capacity = 30;
		fee = 1000;
		status = BusStatus.RUN;
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
			System.out.println(Utils.convertRedErrorMsg("다른 대중교통의 상태 값"));
			return;
		}

		if (status == BusStatus.GARAGE) {
			numberOfPassengers = 0;
		}

		this.status = status;
	}

	@Override
	public void boardingPassengers(int passengers) {
		if (status != BusStatus.RUN) {
			System.out.println(Utils.convertRedErrorMsg("차량이 운행중이지 않습니다."));
			return;
		}

		if (numberOfPassengers + passengers > capacity) {
			System.out.println(Utils.convertRedErrorMsg("최대 승객 수 초과"));
			return;
		}

		numberOfPassengers += passengers;
	}

	@Override
	protected boolean checkFuel() {
		if (vehicle.getFuelingAmount() < FUEL_LOWER_LIMIT) {
			System.out.println(Utils.convertRedErrorMsg("주유 필요"));
			changeState(BusStatus.GARAGE);
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return vehicle +
			"\n탑승 승객 수 = " + numberOfPassengers +
			"\n잔여 승객 수 = " + (capacity - numberOfPassengers) +
			"\n상태 = " + status +
			"\n요금 확인 = " + numberOfPassengers * fee +
			"\n";
	}
}
