package transport;

public class Destination {

	private String name;
	private int distance;

	public Destination(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}

	public int calculateExceedDistance(int basicDistance) {
		return distance - basicDistance < 0 ? 0 : distance - basicDistance;
	}

	@Override
	public String toString() {
		return "목적지 이름 = " + name +
			"\n목적지까지 거리 = " + distance +
			"\n";
	}
}
