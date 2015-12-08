public class ClockManagement {
	public double winProbability(int[] percentageA, int[] percentageB, int[] reboundA,
						  int[] reboundB, int time, int scoreA, int scoreB) {
		// dp[i][j][k] is the probability for A to win in situation that
		// last time is i, current team is j (0 for A, 1 for B), score diff is k
		// (scoreA - scoreB = diff + 250).
		double[][][] dp = new double[time + 1][2][501];
		for (int i = 251; i <= 500; ++i) {
			dp[0][0][i] = dp[0][1][i] = dp[1][0][i] = dp[1][1][i] = 1.0;
		}
		for (int t = 2; t <= time; ++t) {
			for (int diff = 0; diff <= 500; ++diff) {
				// If cur player is A.
				double maxP = 0.0;
				for (int i = 0; i < percentageA.length; ++i) {
					int sec = i + 2;
					if (sec > t) {
						continue;
					}
					double p = 0.0;
					// out of range.
					if (diff + 2 > 500) {
						continue;
					}
					double hit = percentageA[i] / 100.0;
					// hit.
					p += hit * dp[t - sec][1][diff + 2];
					// rebound.
					double rebound = reboundA[i] / 100.0;
					p += (1 - hit) * rebound * dp[t - sec][0][diff];
					p += (1 - hit) * (1 - rebound) * dp[t - sec][1][diff];
					maxP = Math.max(maxP, p);
				}
				dp[t][0][diff] = maxP;
				// If cur player is B.
				double minP = 1.0;
				for (int i = 0; i < percentageB.length; ++i) {
					int sec = i + 2;
					if (sec > t) {
						continue;
					}
					double p = 0.0;
					if (diff - 2 < 0) {
						continue;
					}
					double hit = percentageB[i] / 100.0;
					// hit.
					p += hit * dp[t - sec][0][diff - 2];
					// rebound.
					double rebound = reboundB[i] / 100.0;
					p += (1 - hit) * rebound * dp[t - sec][1][diff];
					p += (1 - hit) * (1 - rebound) * dp[t - sec][0][diff];
					minP = Math.min(minP, p);
				}
				dp[t][1][diff] = minP;
			}
		}
		return dp[time][0][scoreA - scoreB + 250];
	}
}
