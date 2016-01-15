public class FarmvilleDiv2 {
	public int minTime(int[] time, int[] cost, int budget) {
		int n = time.length;
		int[] dp = new int[budget+1];
		for (int i = 0; i < budget; ++i) {
			dp[i] = -1;
		}
		for (int t = 0; t < n; ++t) {
			int[] nextDp = new int[budget+1];
			for (int i = 0; i <= budget; ++i) {
				nextDp[i] = -1;
			}
			for (int i = 0; i <= budget; ++i) {
				if (dp[i] == -1) {
					continue;
				}
				for (int d = 0; d <= time[t]; ++d) {
					int nextBudget = i - d * cost[t];
					if (nextBudget < 0) {
						break;
					}
					int newTime = dp[i] + time[t] - d;
					if (nextDp[nextBudget] == -1 || nextDp[nextBudget] > newTime) {
						nextDp[nextBudget] = newTime;
					}
				}
			}
			dp = nextDp;
		}
		int result = -1;
		for (int i = 0; i <= budget; ++i) {
			if (dp[i] != -1) {
				if (result == -1 || dp[i] < result) {
					result = dp[i];
				}
			}
		}
		return result;
	}
}
