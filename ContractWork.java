public class ContractWork {
	public int minimumCost(String[] costs, int numTasks) {
		int n = costs.length;
		int[][] cost = new int[n][numTasks];
		for (int i = 0; i < n; ++i) {
			String[] strs = costs[i].split(" ");
			for (int j = 0; j < numTasks; ++j) {
				cost[i][j] = Integer.parseInt(strs[j]);
			}
		}
		// dp[i][j] means the min cost to finish previous tasks with last company is i,
		// and it is repeated for j+1 times.
		int[][] dp = new int[n][2];
		for (int t = 0; t < numTasks; ++t) {
			int[][] nextDp = new int[n][2];
			for (int i = 0; i < n; ++i) {
				nextDp[i][0] = -1;
				for (int j = 0; j < n; ++j) {
					if (j == i) {
						continue;
					}
					int cur = Math.min(dp[j][0], dp[j][1]);
					if (nextDp[i][0] == -1 || nextDp[i][0] > cur) {
						nextDp[i][0] = cur;
					}
				}
				nextDp[i][0] += cost[i][t];
				nextDp[i][1] = dp[i][0] + cost[i][t];
			}
			dp = nextDp;
		}
		int result = -1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < 2; ++j) {
				if (result == -1 || result > dp[i][j]) {
					result = dp[i][j];
				}
			}
		}
		return result;
	}
}
