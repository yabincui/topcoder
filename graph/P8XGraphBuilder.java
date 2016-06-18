public class P8XGraphBuilder {
	public int solve(int[] scores) {
		int n = scores.length + 1;
		// basically each node has to have 1 edge, lefting 2 (n - 1) - n = n - 1 extra edges.
		// dp[i][j] means there are i nodes left, and j extra edges left.
		int[][] dp = new int[n + 1][n - 1];
		// dp[0][?] = 0
		for (int i = 1; i <= n; ++i) {
			dp[i][0] = scores[0] * i;
			for (int j = 1; j < n - 1; ++j) {
				// how many extra edges for current node.
				for (int k = 1; k <= j; ++k) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-k] + scores[k]);
				}
			}
		}
		return dp[n][n - 2];
	}
}
