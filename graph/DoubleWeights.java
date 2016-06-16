public class DoubleWeights {
	public int minimalCost(String[] weight1, String[] weight2) {
		int n = weight1.length;
		int max_sum = n * 10;
		// dp[i][j] is the min sum of weight2 if arriving node i with weight1 sum of j.
		int[][] dp = new int[n][max_sum];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < max_sum; ++j) {
				dp[i][j] = -1;
			}
		}
		dp[0][0] = 0;  // start at 0
		for (int step = 1; step < n; ++step) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < max_sum; ++j) {
					if (dp[i][j] != -1) {
						for (int k = 0; k < n; ++k) {
							if (weight1[i].charAt(k) == '.' || weight2[i].charAt(k) == '.') {
								continue;
							}
							int w1 = weight1[i].charAt(k) - '0';
							int w2 = weight2[i].charAt(k) - '0';
							int cost = w2 + dp[i][j];
							if (j + w1 >= max_sum) {
								continue;
							}
							//System.out.printf("j = %d, w1 = %d, max_sum = %d\n", j, w1, max_sum);
							if (dp[k][j + w1] == -1 || dp[k][j + w1] > cost) {
								dp[k][j + w1] = cost;
							}
						}
					}
				}
			}
		}
		int result = -1;
		for (int i = 0; i < max_sum; ++i) {
			if (dp[1][i] != -1) {
				int tmp = i * dp[1][i];
				if (result == -1 || tmp < result) {
					result = tmp;
				}
			}
		}
		return result;
	}
}
