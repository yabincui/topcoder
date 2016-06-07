public class Bonuses {
	public int[] getDivision(int[] points) {
		int sum = 0;
		int n = points.length;
		for (int i = 0; i < n; ++i) {
			sum += points[i];
		}
		int[] result = new int[n];
		for (int i = 0; i < n; ++i) {
			result[i] = points[i] * 100 / sum;
		}
		int left = 100;
		for (int i = 0; i < n; ++i) {
			left -= result[i];
		}
		if (left > 0) {
			boolean[] added = new boolean[n];
			while (left-- > 0) {
				int target = -1;
				for (int i = 0; i < n; ++i) {
					if (!added[i] && (target == -1 || points[i] > points[target])) {
						target = i;
					}
				}
				added[target] = true;
				result[target]++;
			}
		}
		return result;
	}
}
