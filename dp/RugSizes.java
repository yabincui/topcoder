public class RugSizes {
	public int rugCount(int area) {
		int count = 0;
		for (int i = 1; i * i <= area; ++i) {
			if (area % i != 0) {
				continue;
			}
			int b = area / i;
			if (b != i && i % 2 == 0 && b % 2 == 0) {
				continue;
			}
			count++;
		}
		return count;
	}
}
