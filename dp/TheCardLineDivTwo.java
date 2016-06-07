public class TheCardLineDivTwo {
	public int count(String[] cards) {
		final long MOD = 1234567891L;
		int n = cards.length;
		int mask = (1 << n) - 1;
		// dp[i][j] means how many ways to reorder cards (in mask j) with card i as a prefix, i is not in mask j.
		long[][] dp = new long[n][mask + 1];
		for (int i = 0; i < n; ++i) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= mask; ++i) {
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					continue;
				}
				for (int k = 0; k < n; ++k) {
					if ((i & (1 << k)) == 0) {
						continue;
					}
					if (cards[j].charAt(0) == cards[k].charAt(0) ||
							((cards[j].charAt(1) == 'S' || cards[j].charAt(1) == 'C') &&
									(cards[k].charAt(1) == 'S' || cards[k].charAt(1) == 'C')) ||
							((cards[j].charAt(1) == 'D' || cards[j].charAt(1) == 'H') &&
									(cards[k].charAt(1) == 'D' || cards[k].charAt(1) == 'H'))) {
						dp[j][i] = (dp[j][i] + dp[k][(i & ~(1 << k))]) % MOD;
					}
				}
			}
		}
		long result = 0;
		for (int i = 0; i < n; ++i) {
			result = (result + dp[i][mask & ~(1 << i)]) % MOD;
		}
		return (int)result;
	}
}