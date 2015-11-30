public class HillHike {
	public long numPaths(int distance, int maxHeight, int[] landmarks) {
		int n = landmarks.length;
		// 100 * 50 * 50 * 2. 5*10^5.
		// dp[i][j][k][t] is the ways to reach height j at distance i, having passed
		// k landmarks, having passed maxHeight if t == 1.
		long[][][][] dp = new long[distance + 1][maxHeight + 1][n + 1][2];
		dp[0][0][0][0] = 1;
		for (int i = 0; i < distance; ++i) {
			for (int j = 0; j <= maxHeight; ++j) {
				for (int k = 0; k <= n; ++k) {
					for (int t = 0; t < 2; ++t) {
						if (j == maxHeight && t == 0) {
							continue;
						}
						// Move to j - 1.
						if (j > 1 || (j == 1 && i == distance - 1)) {
							int nextK = k;
							if (k < n && j - 1 == landmarks[k]) {
								nextK++;
							}
							dp[i+1][j-1][nextK][t] += dp[i][j][k][t];
						}
						// Move to j.
						if (j >= 1) {
							int nextK = k;
							if (k < n && j == landmarks[k]) {
								nextK++;
							}
							dp[i+1][j][nextK][t] += dp[i][j][k][t];
						}
						// Move to j + 1.
						if (j < maxHeight) {
							int nextK = k;
							if (k < n && j + 1 == landmarks[k]) {
								nextK++;
							}
							int nextT = t;
							if (j + 1 == maxHeight) {
								nextT = 1;
							}
							dp[i+1][j+1][nextK][nextT] += dp[i][j][k][t];
						}
					}
				}
			}
		}
		/*
		for (int i = 0; i <= distance; ++i) {
			for (int j = 0; j <= maxHeight; ++j) {
				for (int k = 0; k <= n; ++k) {
					for (int t = 0; t < 2; ++t) {
						System.out.printf("dp[%d][%d][%d][%d] = %d\n", i, j, k, t, dp[i][j][k][t]);
					}
				}
			}
		}
		*/
		return dp[distance][0][n][1];
	}
}
