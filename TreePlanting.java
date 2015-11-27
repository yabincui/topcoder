public class TreePlanting {
	public long countArrangements(int total, int fancy) {
		// dp[i][j][k] is the number of ways planting i trees with j fancy, last is fancy if k == 1.
		long[][][] dp = new long[total + 1][fancy + 1][2];
		dp[0][0][0] = 1;
		for (int i = 1; i <= total; ++i) {
			for (int j = 0; j <= Math.min(i, fancy); ++j) {
				for (int k = 0; k < 2; ++k) {
					long ways = 0;
					// Plant normal tree.
					if (k == 0) {
						ways += dp[i-1][j][0] + dp[i-1][j][1];
					}
					// Plant fancy tree.
					if (j > 0 && k == 1) {
						ways += dp[i-1][j-1][0];
					}
					dp[i][j][k] = ways;
				}
			}
		}
		return dp[total][fancy][0] + dp[total][fancy][1];
	}
}
