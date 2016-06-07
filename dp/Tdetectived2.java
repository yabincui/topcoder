public class Tdetectived2 {
	public int reveal(String[] s) {
		int[] init = new int[s[0].length()-1];
		for (int i = 1; i < s[0].length(); ++i) {
			init[i-1] = s[0].charAt(i) - '0';
		}
		int n = s.length - 1;
		int[][] dislike = new int[n][n];
		for (int i = 1; i < s.length; ++i) {
			for (int j = 1; j < s[i].length(); ++j) {
				dislike[i-1][j-1] = s[i].charAt(j) - '0';
			}
		}
		int mask = (1 << n) - 1;
		int[] candidates = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			int[] curDislike = new int[n];
			for (int j = 0; j < n; ++j) {
				curDislike[j] = init[j];
			}
			for (int bit = 0; bit < n; ++bit) {
				if ((i & (1 << bit)) == 0) {
					for (int j = 0; j < n; ++j) {
						curDislike[j] = Math.max(curDislike[j], dislike[bit][j]);
					}
				}
			}
			int curMax = -1;
			int curCandidate = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					if (curDislike[j] > curMax) {
						curCandidate = 1 << j;
						curMax = curDislike[j];
					} else if (curDislike[j] == curMax) {
						curCandidate |= 1 << j;
					}
				}
			}
			candidates[i] = curCandidate;
		}
		int[][] dp = new int[mask + 1][n];
		for (int i = 0; i < n; ++i) {
			dp[1<<i][i] = 1;
		}
		for (int i = 1; i <= mask; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dp[i][j] != 0 || (i & (1<<j)) == 0) {
					continue;
				}
				if ((candidates[i] & (1 << j)) != 0) {
					dp[i][j] = 1;
				} else {
					int min = n;
					for (int k = 0; k < n; ++k) {
						if ((candidates[i] & (1 <<k)) == 0) {
							continue;
						}
						min = Math.min(min, dp[i & ~(1<<k)][j] + 1);
					}
					dp[i][j] = min;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			result += (i + 1) * dp[mask][i];
		}
		return result;
	}
}
