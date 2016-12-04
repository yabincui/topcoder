public class FleasFleas {
	public int population(int n, int k) {
		int result = n;
		if (k > 0) {
			int value = population(n, k - 5);
			if (value == -1) {
				return -1;
			}
			int tmp = value * k;
			result += tmp;
			if (result > 10000000) {
				return -1;
			}
		}
		return result;
	}
}
