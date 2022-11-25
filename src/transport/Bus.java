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
		status = TransportationStatus.BUS_RUN;
		fee = 1000;
	}

	@Override
	public void departure() {
		if (refuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = TransportationStatus.BUS_GARAGE;
			return;
		}


	}

	@Override
	public void changeVelocity(int velocity) {
		if (refuelingAmount < 10) {
			System.out.println("주유량을 확인해 주세요.");
			status = TransportationStatus.BUS_GARAGE;
			return;
		}

		this.velocity += velocity;
		if (velocity < 0) {
			velocity = 0;
		}
	}

	@Override
	public void changeState() {

	}

	@Override
	public void boardingPassengers(int passengers) {
		if (status != TransportationStatus.BUS_RUN) {
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
