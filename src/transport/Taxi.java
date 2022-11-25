package transport;

import java.util.UUID;

public class Taxi extends PublicTransportation {

	private Destination destination;
	private int basicMoveDistance;
	private int feeForDistance;

	public Taxi() {
		number = UUID.randomUUID();
		refuelingAmount = 100;
		fee = 3000;
		velocity = 0;
		capacity = 4;
		status = TaxiStatus.NORMAL;
		basicMoveDistance = 1;
		feeForDistance = 1000;
		destination = null;
	}

	@Override
	public void departure() {
		if (refuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = TaxiStatus.NORMAL;
			return;
		}

	}

	@Override
	public void changeVelocity(int velocity) {
		this.velocity += velocity;
		if (velocity < 0) {
			velocity = 0;
		}
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
		if (status != TaxiStatus.NORMAL) {
			System.out.println("차량이 운행중이지 않습니다.");
			return;
		}

		if (passengers > capacity) {
			System.out.println("탑승 인원을 초과했습니다.");
			return;
		}

		status = TaxiStatus.RUN;
	}


	public void calculateFeePerDistance() {
		feeForDistance *= (destination.getDistance() - basicMoveDistance);
	}

	public void pay() {
		calculateFeePerDistance();
		int payAmount = fee + feeForDistance;
		System.out.println("최종 요금 "+ payAmount + " 원");
	}
}
