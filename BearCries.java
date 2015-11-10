public class BearCries {
	public int count(String message) {
		long MOD = 1000000007;
		int n = message.length();
		// dp[i][j] is the ways having i unfinished positive ;, j unfinished empty ;.
		int[][] dp = new int[n+1][n+1];
		dp[0][0] = 1;
		for (int i = 0; i < n; ++i) {
			char c = message.charAt(i);
			int[][] nextDp = new int[n+1][n+1];
			if (c == '_') {
				for (int j = 0; j <= n; ++j) {
					for (int k = 0; k <= n; ++k) {
						if (dp[j][k] == 0) {
							continue;
						}
						if (j > 0) {
							nextDp[j][k] = (int)((nextDp[j][k] + (long)dp[j][k] * j) % MOD);
						}
						if (k != 0) {
							nextDp[j+1][k-1] = (int)((nextDp[j+1][k-1] + (long)dp[j][k] * k) % MOD);
						}
					}
				}
			} else {
				for (int j = 0; j <= n; ++j) {
					for (int k = 0; k <= n; ++k) {
						if (dp[j][k] == 0) {
							continue;
						}
						// Finish a positive ;.
						if (j > 0) {
							nextDp[j-1][k] = (int)((nextDp[j-1][k] + (long)dp[j][k] * j) % MOD);
						}
						// Start a new empty ;.
						nextDp[j][k+1] = (int)((nextDp[j][k+1] + (long)dp[j][k]) % MOD);
					}
				}
			}
			dp = nextDp;
		}
		return dp[0][0];
	}
}
