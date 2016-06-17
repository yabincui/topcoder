public class ShortestPathWithMagic {
	public double getTime(String[] dist, int k) {
		int n = dist.length;
		// dp[i][j] is the min cost reaching city i with j potions left.
		double[][] dp = new double[n][k+1];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= k; ++j) {
				dp[i][j] = -1;
			}
		}
		dp[0][k] = 0;
		for (int step = 0; step < n; ++step) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j <= k; ++j) {
					if (dp[i][j] == -1) {
						continue;
					}
					for (int t = 0; t < n; ++t) {
						if (t == i) {
							continue;
						}
						double cost_without_potion = dist[i].charAt(t) - '0';
						double cost_with_potion = cost_without_potion / 2.0;
						if (dp[t][j] == -1 || dp[t][j] > dp[i][j] + cost_without_potion) {
							dp[t][j] = dp[i][j] + cost_without_potion;
						}
						if (j > 0) {
							if (dp[t][j-1] == -1 || dp[t][j - 1] > dp[i][j] + cost_with_potion) {
								dp[t][j-1] = dp[i][j] + cost_with_potion;
							}
						}
					}
				}
			}
		}
		double result = -1;
		for (int i = 0; i <= k; ++i) {
			if (dp[1][i] != -1) {
				if (result == -1 || result > dp[1][i]) {
					result = dp[1][i];
				}
			}
		}
		return result;
	}
}
