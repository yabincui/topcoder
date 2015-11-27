public class LoadBalancing {
	public int minTime(int[] chunkSizes) {
		int n = chunkSizes.length;
		for (int i = 0; i < n; ++i) {
			chunkSizes[i] /= 1024;
		}
		int sum = 0;
		for (int i = 0; i < n; ++i) {
			sum += chunkSizes[i];
		}
		boolean[] diff = new boolean[sum + 1];
		diff[0] = true;
		for (int i = 0; i < n; ++i) {
			boolean[] nextDiff = new boolean[sum + 1];
			for (int j = 0; j <= sum; ++j) {
				if (diff[j]) {
					// Increase diff.
					nextDiff[j + chunkSizes[i]] = true;
					// Decrease diff.
					int t = Math.abs(j - chunkSizes[i]);
					nextDiff[t] = true;
				}
			}
			diff = nextDiff;
		}
		int minDiff = sum;
		for (int i = 0; i <= sum; ++i) {
			if (diff[i]) {
				minDiff = i;
				break;
			}
		}
		int a = (sum + minDiff) / 2;
		return a * 1024;
	}
}
