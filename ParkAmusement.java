public class ParkAmusement {
	public double getProbability(String[] landings, int startLanding, int K) {
		int n = landings.length;
		
		// dp[i][j] is the probability to safely come back home with j pipes.
		double[][] dp = new double[n][K + 1];
		for (int i = 0; i < n; ++i) {
			if (landings[i].contains("E")) {
				dp[i][0] = 1.0;
			}
		}
		for (int i = 1; i <= K; ++i) {
			for (int j = 0; j < n; ++j) {
				if (landings[j].contains("E") || landings[j].contains("P")) {
					continue;
				}
				int nextCount = 0;
				double successProbability = 0.0;
				for (int k = 0; k < n; ++k) {
					if (landings[j].charAt(k) == '1') {
						nextCount++;
						successProbability += dp[k][i - 1];
					}
				}
				dp[j][i] = successProbability / nextCount;
			}
		}
		double totalProbability = 0.0;
		for (int i = 0; i < n; ++i) {
			if (dp[i][K] != 0) {
				totalProbability += dp[i][K];
			}
		}
		return dp[startLanding][K] / totalProbability;
	}
}