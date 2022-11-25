package transport;

public class Taxi extends PublicTransportation {
	private static int TAXI_ID = 0;

	private Destination destination;
	private int basicFeeDistance;
	private int feeForDistance;

	public Taxi() {
		vehicleNumber = TAXI_ID++;
		fuelingAmount = 100;
		fee = 3000;
		velocity = 0;
		numberOfPassengers = 0;
		capacity = 4;
		status = TaxiStatus.NORMAL;
		basicFeeDistance = 1;
		feeForDistance = 1000;
		destination = null;
	}

	@Override
	public void departure() {
		// 요구사항
		if (fuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = TaxiStatus.NORMAL;
			return;
		}

		// 추가
		changeVelocity(40);
		fuelingAmount -= 10;
	}

	@Override
	public void changeVelocity(int velocity) {
		// 요구사항
		this.velocity += velocity;
		if (velocity < 0) {
			velocity = 0;
		}

		// 추가
		fuelingAmount -= 2;
	}

	@Override
	public void changeState(TransportationStatus status) {
		if (!(status instanceof TaxiStatus)) {
			System.out.println("다른 대중교통의 상태 값 입니다.");
			return;
		}

		this.status = status;
	}

	@Override
	public void boardingPassengers(int passengers) {
		// 요구사항
		if (status != TaxiStatus.NORMAL) {
			System.out.println("차량이 운행중이지 않습니다.");
			return;
		}

		if (numberOfPassengers + passengers > capacity) {
			System.out.println("탑승 인원을 초과했습니다.");
			return;
		}
		numberOfPassengers += passengers;
		status = TaxiStatus.RUN;

		// 추가
		fuelingAmount -= 2;
	}


	private int calculateFeePerDistance() {
		int exceedDistance = destination.getDistance() - basicFeeDistance;
		if (exceedDistance < 0) {
			exceedDistance = 0;
		}
		return feeForDistance *= exceedDistance;
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

	public void pay() {
		int exceedFee = calculateFeePerDistance();
		int payAmount = fee + exceedFee;
		System.out.println("최종 요금 "+ payAmount + " 원");
	}
}
