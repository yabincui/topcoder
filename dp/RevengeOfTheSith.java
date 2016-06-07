public class RevengeOfTheSith {
	public int move(int[] steps, int T, int D) {
		int n = steps.length;
		int[] heights = new int[n+1];
		for (int i = 0; i < n; ++i) {
			heights[i+1] = heights[i] + steps[i];
		}
		// dp[i][j][k] is to fix level i and level j, using at most k floating levels,
		// get the minimum cost to reach i from j.
		int[][][] dp = new int[n + 1][n + 1][T + 1];
		for (int step = 1; step <= n; ++step) {
			for (int i = 0; i <= n - step; ++i) {
				int j = i + step;
				for (int t = 0; t <= T; ++t) {
					int middleLevels = j - i - 1;
					if (middleLevels <= t) {
						// Average the space.
						int totalDist = heights[j] - heights[i];
						int averageDist = totalDist / (middleLevels + 1);
						int rem = totalDist % (middleLevels + 1);
						int cost = 0;
						int dist1 = averageDist;
						if (dist1 > D) {
							cost += (middleLevels + 1 - rem) * (dist1 - D) * (dist1 - D);
						}
						int dist2 = averageDist + 1;
						if (dist2 > D) {
							cost += rem * (dist2 - D) * (dist2 - D);
						}
						dp[i][j][t] = cost;
					} else {
						// Select one middle level that doesn't move.
						int cost = Integer.MAX_VALUE;
						for (int k = i + 1; k < j; ++k) {
							for (int t1 = 0; t1 <= t; ++t1) {
								cost = Math.min(cost, dp[i][k][t1] + dp[k][j][t - t1]);
							}
						}
						dp[i][j][t] = cost;
					}
					//System.out.printf("dp[%d][%d][%d] = %d\n", i, j, t, dp[i][j][t]);
				}
			}
		}
		return dp[0][n][T];
	}
}
