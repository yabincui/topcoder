public class WindowWasher {
	public int fastest(int width, int height, int[] washTimes) {
		int n = washTimes.length;
		int[] dp = new int[n];
		for (int i = 0; i < width; ++i) {
			int minCost = -1;
			int bestWorker = -1;
			for (int j = 0; j < n; ++j) {
				int cost = dp[j] + washTimes[j] * height;
				if (minCost == -1 || minCost > cost) {
					minCost = cost;
					bestWorker = j;
				}
			}
			dp[bestWorker] += washTimes[bestWorker] * height;
		}
		int result = -1;
		for (int i = 0; i < n; ++i) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
