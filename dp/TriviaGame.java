import java.util.*;

public class TriviaGame {
	public int maximumScore(int[] points, int tokensNeeded, int[] bonuses) {
		int n = points.length;
		// dp[i][j] is the max score can when when at pos i - 1, and having j tokens.
		int[][] dp = new int[n + 1][tokensNeeded];
		for (int i = 0; i <= n; ++i) {
			for (int j = 0; j < tokensNeeded; ++j) {
				dp[i][j] = Integer.MIN_VALUE;
			}
		}
		dp[0][0] = 0;
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < tokensNeeded; ++j) {
				if (dp[i-1][j] != Integer.MIN_VALUE) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j] - points[i-1]);
				}
				if (j > 0) {
					if (dp[i-1][j-1] != Integer.MIN_VALUE) {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + points[i-1]);
					}
				} else {
					if (dp[i-1][tokensNeeded - 1] != Integer.MIN_VALUE) {
						dp[i][0] = Math.max(dp[i][0], dp[i-1][tokensNeeded - 1] + points[i-1] + bonuses[i-1]);
					}
				}
			}
		}
		int maxResult = 0;
		for (int i = 0; i < tokensNeeded; ++i) {
			maxResult = Math.max(maxResult, dp[n][i]);
		}
		return maxResult;
	}
}