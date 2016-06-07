public class BlockDistance {
	public int minDist(String[] oldText, String[] newText) {
		StringBuilder oldBuilder = new StringBuilder();
		for (int i = 0; i < oldText.length; ++i) {
			oldBuilder.append(oldText[i]);
		}
		StringBuilder newBuilder = new StringBuilder();
		for (int i = 0; i < newText.length; ++i) {
			newBuilder.append(newText[i]);
		}
		String oldS = oldBuilder.toString();
		String newS = newBuilder.toString();
	
		int m = oldS.length();
		int n = newS.length();
		// dp[i][j] is the min block dist to match oldS[0..i-1] and newS[0..j-1].
		final int INF = 1000000;
		int[][] dp = new int[m+1][n+1];
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				dp[i][j] = INF;
			}
		}
		dp[0][0] = 0;
		for (int i = 1; i <= n; ++i) {
			dp[0][i] = 1;
		}
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (oldS.charAt(i-1) == newS.charAt(j-1)) {
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
				}
				for (int k = j-1; k >= 0; --k) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + 1);
				}
			}
		}
		return (dp[m][n] == INF) ? -1 : dp[m][n]; 
	}
}
