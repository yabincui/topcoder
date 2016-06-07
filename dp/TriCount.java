public class TriCount {
	public int count(int minLength, int maxLength) {
		long sum = 0;
		int limit = 1000000000;
		for (int a = minLength; a <= maxLength; ++a) {
			int n1 = (maxLength + 1 - a - a + 1);
			if (n1 > 0) {
				sum += (long)n1 * a;
				if (sum > limit) {
					return -1;
				}
			}
			int b1 = maxLength + 2 - a;
			int b2 = maxLength;
			if (b1 < a) {
				b1 = a;
			}
			int count = b2 - b1 + 1;
			int min = maxLength - b1 + 1;
			int max = maxLength - b2 + 1;
			long tmp = (long)count * (max + min) / 2;
			sum += tmp;
			if (sum > limit) {
				return -1;
			}
		}
		return (int)sum;
	}
}
