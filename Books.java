public class Books {
	public int sortMoves(String[] titles) {
		int n = titles.length;
		int mask = (1 << n) - 1;
		// dp[i] is the min moves to sort books in mask i.
		int[] dp = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			int leftest = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					leftest = j;
					break;
				}
			}
			int minMask = 0;
			String minValue = "";
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					if (minMask == 0 || minValue.compareTo(titles[j]) > 0) {
						minMask = 1 << j;
						minValue = titles[j];
					} else if (minValue.compareTo(titles[j]) == 0) {
						minMask |= 1 << j;
					}
				}
			}
			if ((minMask & (1 << leftest)) != 0) {
				dp[i] = dp[i & ~(1 << leftest)];
			} else {
				// Insert the leftest book into its right position.
				dp[i] = dp[i & ~(1 << leftest)] + 1;
				// Insert one min book into its right position.
				for (int j = 0; j < n; ++j) {
					if ((minMask & (1 << j)) != 0) {
						dp[i] = Math.min(dp[i], dp[i & ~(1 << j)] + 1);
					}
				}
			}
		}
		return dp[mask];
	}
}
