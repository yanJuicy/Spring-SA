package transport;

import java.util.UUID;

public class Bus extends PublicTransportation {

	private int numberOfPassengers;

	public Bus() {
		number = UUID.randomUUID();
		numberOfPassengers = 0;
		capacity = 30;
		refuelingAmount = 100;
		velocity = 0;
		status = BusStatus.RUN;
		fee = 1000;
	}

	@Override
	public void departure() {
		// 요구사항
		if (refuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = BusStatus.GARAGE;
			return;
		}

		// 추가
		changeVelocity(40);
		refuelingAmount -= 10;
	}

	@Override
	public void changeVelocity(int velocity) {
		// 요구사항
		if (refuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = BusStatus.GARAGE;
			return;
		}

		this.velocity += velocity;
		if (velocity < 0) {
			velocity = 0;
		}

		// 추가
		refuelingAmount -= 2;
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
	}
}
