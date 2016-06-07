public class ThePriceIsRight {
	public int[] howManyReveals(int[] prices) {
		int n = prices.length;
		int[] dp = new int[n];
		int[] ways = new int[n];
		dp[0] = 1;
		ways[0] = 1;
		for (int i = 1; i < n; ++i) {
			dp[i] = 1;
			ways[i] = 1;
			for (int j = i - 1; j >= 0; --j) {
				if (prices[j] < prices[i]) {
					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
						ways[i] = ways[j];
					} else if (dp[i] == dp[j] + 1) {
						ways[i] += ways[j];
					}
				}
			}
		}
		int maxLen = 0;
		int wayCount = 0;
		for (int i = 0; i < n; ++i) {
			if (dp[i] > maxLen) {
				maxLen = dp[i];
				wayCount = ways[i];
			} else if (dp[i] == maxLen) {
				wayCount += ways[i];
			}
		}
		return new int[]{maxLen, wayCount};
	}
}
