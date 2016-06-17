public class PathGameDiv2 {
	public int calc(String[] board) {
		int n = board[0].length();
		// dp[i][0] is the max erase count making only row 0 white
		// dp[i][1] is the max erase count making only row 1 white
		// dp[i][2] is the max erase count making both row white
		int[][] dp = new int[n+1][3];
		for (int i = 0; i <= n; ++i) {
			for (int j = 0; j < 3; ++j) {
				dp[i][j] = -1;
			}
		}
		dp[0][2] = 0;
		for (int i = 1; i <= n; ++i) {
			boolean c1 = board[0].charAt(i - 1) == '.';
			boolean c2 = board[1].charAt(i - 1) == '.';
			if (c1 && !c2) {
				int prev = Math.max(dp[i-1][0], dp[i-1][2]);
				if (prev != -1) {
					dp[i][0] = prev;
				}
			} else if (!c1 && c2) {
				int prev = Math.max(dp[i-1][1], dp[i-1][2]);
				if (prev != -1) {
					dp[i][1] = prev;
				}
			} else if (c1 && c2) {
				int prev = Math.max(dp[i-1][2], Math.max(dp[i-1][0], dp[i-1][1]));
				if (prev != -1) {
					dp[i][2] = prev;
				}
				prev = Math.max(dp[i-1][0], dp[i-1][2]);
				if (prev != -1) {
					dp[i][0] = prev + 1;
				}
				prev = Math.max(dp[i-1][1], dp[i-1][2]);
				if (prev != -1) {
					dp[i][1] = prev + 1;
				}
			}
		}
		int result = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
		return result;
	}
}
