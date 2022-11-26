package transport;

import util.Utils;

public class Taxi extends PublicTransportation {
	private static final int FUEL_LOWER_LIMIT = 10;
	private static final int FEE_FOR_EXCEED_DISTANCE = 1000;
	private static final int BASIC_FEE_DISTANCE = 1;
	private static int TAXI_ID = 0;

	private int payAmount;
	private int totalPay;

	public Taxi() {
		vehicle = new Vehicle(TAXI_ID++, 100, 0);
		numberOfPassengers = 0;
		capacity = 4;
		fee = 3000;
		status = TaxiStatus.NORMAL;
		payAmount = 0;
		totalPay = 0;
	}

	@Override
	public void departure() {
		if (!checkFuel()) {
			return;
		}

		changeState(TaxiStatus.NORMAL);
	}

	@Override
	public void changeState(TransportationStatus status) {
		if (!(status instanceof TaxiStatus)) {
			System.out.println(Utils.convertRedErrorMsg("다른 대중교통의 상태 값"));
			return;
		}

		if (status == TaxiStatus.IMPOSSIBLE) {
			numberOfPassengers = 0;
		}

		this.status = status;
	}

	@Override
	public void boardingPassengers(int passengers) {
		if (status != TaxiStatus.NORMAL) {
			System.out.println(Utils.convertRedErrorMsg("차량이 운행중이지 않습니다."));
			return;
		}

		if (numberOfPassengers + passengers > capacity) {
			System.out.println(Utils.convertRedErrorMsg("최대 승객 수 초과"));
			return;
		}

		changeState(TaxiStatus.RUN);
		numberOfPassengers += passengers;
		payAmount = calculatePay();
	}

	@Override
	protected boolean checkFuel() {
		if (vehicle.getFuelingAmount() < FUEL_LOWER_LIMIT) {
			System.out.println(Utils.convertRedErrorMsg("주유 필요"));
			changeState(TaxiStatus.IMPOSSIBLE);
			numberOfPassengers = 0;
			return false;
		}

		return true;
	}

	@Override
	public void pay() {
		totalPay += payAmount;
		System.out.println("최종 요금 " + payAmount + " 원\n");
		numberOfPassengers = 0;
		changeState(TaxiStatus.NORMAL);
	}

	private int calculatePay() {
		int exceedDistance = destination.calculateExceedDistance(BASIC_FEE_DISTANCE);
		int exceedFee = FEE_FOR_EXCEED_DISTANCE * exceedDistance;
		int pay = fee + exceedFee;
		return pay;
	}

	@Override
	public String toString() {
		return vehicle +
			"\n탑승 승객 수 = " + numberOfPassengers +
			"\n잔여 승객 수 = " + (capacity - numberOfPassengers) +
			"\n기본 요금 확인 = " + fee +
			"\n" + destination +
			"\n상태 = " + status +
			"\n지불할 요금 = " + payAmount + "원" +
			"\n누적 요금 = " + totalPay + "원" +
			"\n";
	}
}
