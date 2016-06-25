public class Dubs {
	public long count(long L, long R) {
		if (L + 300 > R) {
			return countRange(L, R + 1);
		}
		long result = 0;
		long a = L / 100 * 100 + 100;
		long b = R / 100 * 100;
		result += countRange(L, a);
		result += countRange(b, R + 1);
		// range [a, b)
		result += (b - a) / 10;
		return result;
	}
	
	private long countRange(long L, long R) {
		long result = 0;
		for (long a = L; a < R; ++a) {
			if (a % 10 == a / 10 % 10 && (a % 10 != 0 || a >= 100)) {
				result++;
			}
		}
		return result;
	}
}
