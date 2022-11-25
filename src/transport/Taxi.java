package transport;

import util.Utils;

public class Taxi extends PublicTransportation {
	private static int TAXI_ID = 0;
	private static int FUEL_LOWER_LIMIT = 10;
	private static int CAPACITY = 4;
	private static int FEE = 3000;
	private static int FEE_FOR_EXCEED_DISTANCE = 1000;
	private static int BASIC_FEE_DISTANCE = 1;
	private Destination destination;

	public Taxi() {
		vehicleNumber = TAXI_ID++;
		fuelingAmount = 100;
		velocity = 0;
		numberOfPassengers = 0;
		status = TaxiStatus.NORMAL;
		destination = null;
	}

	@Override
	public void departure() {
		if(!checkFuel()) {
			return;
		}

		status = TaxiStatus.NORMAL;
	}

	@Override
	public void changeState(TransportationStatus status) {
		if (!(status instanceof TaxiStatus)) {
			System.out.println(Utils.convertRedErrorMsg("다른 대중교통의 상태 값"));
			return;
		}

		this.status = status;
	}

	@Override
	public void boardingPassengers(int passengers) {
		if (status != TaxiStatus.NORMAL) {
			System.out.println(Utils.convertRedErrorMsg("차량이 운행중이지 않습니다."));
			return;
		}

		if (numberOfPassengers + passengers > CAPACITY) {
			System.out.println(Utils.convertRedErrorMsg("최대 승객 수 초과"));
			return;
		}

		numberOfPassengers += passengers;
		status = TaxiStatus.RUN;
	}

	private int calculateFeePerDistance() {
		int exceedDistance = destination.getDistance() - BASIC_FEE_DISTANCE;
		if (exceedDistance < 0) {
			exceedDistance = 0;
		}
		return FEE_FOR_EXCEED_DISTANCE *= exceedDistance;
	}


	@Override
	protected boolean checkFuel() {
		if (fuelingAmount < FUEL_LOWER_LIMIT) {
			System.out.println(Utils.convertRedErrorMsg("주유 필요"));
			changeState(TaxiStatus.IMPOSSIBLE);
			return false;
		}

		return true;
	}

	@Override
	public void pay() {
		int exceedFee = calculateFeePerDistance();
		int payAmount = FEE + exceedFee;
		System.out.println("최종 요금 " + payAmount + " 원");
	}
}
