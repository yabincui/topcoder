public class EqualTowers {
	public int height(int[] bricks) {
		int n = bricks.length;
		if (n < 2) {
			return -1;
		}
		int sum = 0;
		for (int i = 0; i < n; ++i) {
			sum += bricks[i];
		}
		
		// dp[i] means when the diff is i, the maximum base height.
		// If dp[i] = -1, it means a diff of i is impossible.
		int[] dp = new int[sum + 1];
		dp[0] = 0;
		for (int i = 1; i <= sum; ++i) {
			dp[i] = -1;
		}
		for (int i = 0; i < n; ++i) {
			int[] newDp = new int[sum + 1];
			// Ignore it.
			for (int j = 0; j <= sum; ++j) {
				newDp[j] = dp[j];
			}
			for (int j = sum - bricks[i]; j >= 0; --j) {
				if (dp[j] != -1) {
					// Add to j.
					int newDiff = j + bricks[i];
					if (dp[j] > newDp[newDiff]) {
						newDp[newDiff] = dp[j];
					}
					// Add to 0.
					newDiff = Math.abs(j - bricks[i]);
					if (dp[j] + Math.min(j,  bricks[i]) > newDp[newDiff]) {
						newDp[newDiff] = dp[j] + Math.min(j, bricks[i]);
					}
				}
			}
			dp = newDp;
		}
		if (dp[0] != 0) {
			return dp[0];
		}
		
		return -1;
	}
}