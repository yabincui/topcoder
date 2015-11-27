public class BadSubstring {
	public long howMany(int length) {
		// dp[i][0] is the count of length i string not ended with a.
		// dp[i][1] is the count of length i string ended with a.
		long[][] dp = new long[length+1][2];
		long result = 0;
		dp[0][0] = 1;
		for (int i = 1; i <= length; ++i) {
			// dp[i][0], ended with b.
			dp[i][0] += dp[i-1][0];
			// dp[i][0], ended with c.
			dp[i][0] += dp[i-1][0] + dp[i-1][1];
			// dp[i][0], ended with a.
			dp[i][1] += dp[i-1][0] + dp[i-1][1];
		}
		return dp[length][0] + dp[length][1];
	}
}
