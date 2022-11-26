import transport.Bus;
import transport.BusStatus;
import transport.PublicTransportation;
import transport.Taxi;

public class Main {
	public static void main(String[] args) {
		scenario1();
		scenario2();
	}

	private static void scenario1() {
		PublicTransportation bus1 = new Bus();
		PublicTransportation bus2 = new Bus();
		System.out.println(bus1.isSameNumber(bus2));

		bus1.boardingPassengers(2);
		System.out.println(bus1);

		bus1.changeFuel(-50);
		System.out.println(bus1);

		bus1.changeState(BusStatus.GARAGE);
		System.out.println(bus1);

		bus1.changeFuel(10);
		System.out.println(bus1);

		bus1.changeState(BusStatus.RUN);
		System.out.println(bus1);

		bus1.boardingPassengers(45);

		bus1.boardingPassengers(5);
		System.out.println(bus1);

		bus1.changeFuel(-55);
		System.out.println(bus1);
	}

	private static void scenario2() {
		PublicTransportation taxi = new Taxi();
		PublicTransportation taxi2 = new Taxi();
		System.out.println(taxi2.isSameNumber(taxi));
		System.out.println(taxi);
		System.out.println(taxi2);

		taxi.changeDestination("서울역", 2);
		taxi.boardingPassengers(2);
		System.out.println(taxi);

		taxi.changeFuel(-80);
		taxi.pay();
		System.out.println(taxi);

		taxi.boardingPassengers(5);

		taxi.changeDestination("구로디지털단지역", 12);
		taxi.boardingPassengers(3);
		System.out.println(taxi);

		taxi.changeFuel(-20);
		taxi.pay();
		System.out.println(taxi);
	}
}