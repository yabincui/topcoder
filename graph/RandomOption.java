public class RandomOption {
	public double getProbability(int keyCount, int[] badLane1, int[] badLane2) {
		boolean[][] bad = new boolean[keyCount][keyCount];
		for (int i = 0; i < badLane1.length; ++i) {
			bad[badLane1[i]][badLane2[i]] = bad[badLane2[i]][badLane1[i]] = true;
		}
		int bitMask = (1 << keyCount) - 1;
		// dp[i][j] is the count of situations with bit mask j keys used, the last key is i.
		long[][] dp = new long[bitMask + 1][keyCount];
		dp[0][0] = 1;
		for (int i = 0; i < bitMask; ++i) {
			for (int j = 0; j < keyCount; ++j) {
				if (dp[i][j] == 0) continue;
				for (int k = 0; k < keyCount; ++k) {
					if ((i & (1 << k)) != 0 || (bad[j][k] && i != 0)) {
						continue;
					}
					dp[i | (1 << k)][k] += dp[i][j];
				}
			}
		}
		long total = 1;
		for (int i = 2; i <= keyCount; ++i) {
			total *= i;
		}
		long valid = 0;
		for (int i = 0; i < keyCount; ++i) {
			valid += dp[bitMask][i];
			//System.out.printf("dp[%d][%d] = %d\n", bitMask, i, dp[bitMask][i]);
		}
		//System.out.printf("total = %d, valid = %d\n", total, valid);
		return (double)valid / total;
	}
}
