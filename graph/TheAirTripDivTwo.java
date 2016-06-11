public class TheAirTripDivTwo {
	public int find(int[] flights, int fuel) {
		for (int i = 0; i < flights.length; ++i) {
			if (fuel < flights[i]) {
				return i;
			}
			fuel -= flights[i];
		}
		return flights.length;
	}
}
