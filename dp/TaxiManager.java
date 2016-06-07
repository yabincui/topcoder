import java.util.*;

public class TaxiManager {
	public int schedule(String[] roads, String[] customers) {
		int n = roads.length;
		int[][] connect = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int value = roads[i].charAt(j) - '0';
				if (value == 0 && i != j) {
					value = Integer.MAX_VALUE;
				}
				connect[i][j] = value;
			}
		}
		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (connect[i][k] != Integer.MAX_VALUE && connect[k][j] != Integer.MAX_VALUE) {
						if (connect[i][j] > connect[i][k] + connect[k][j]) {
							connect[i][j] = connect[i][k] + connect[k][j];
						}
					}
				}
			}
		}
		int m = customers.length;
		int[] from = new int[m];
		int[] to = new int[m];
		for (int i = 0; i < m; ++i) {
			String[] strs = customers[i].split(" ");
			from[i] = Integer.parseInt(strs[0]);
			to[i] = Integer.parseInt(strs[1]);
		}
		int mask = (1 << m) - 1;
		
		// dp[i][j] is the cost of a car to send customer mask i, the end location is at j.
		int[][] dp = new int[mask + 1][n];
		for (int i = 1; i < n; ++i) {
			dp[0][i] = Integer.MAX_VALUE;
		}
		dp[0][0] = 0;
		for (int i = 1; i <= mask; ++i) {
			for (int j = 0; j < n; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
			}
			for (int prevEnd = 0; prevEnd < n; ++prevEnd) {
				for (int curSend = 0; curSend < m; ++curSend) {
					if ((i & (1 << curSend)) == 0) {
						continue;
					}
					if (dp[i & ~(1 << curSend)][prevEnd] == Integer.MAX_VALUE) {
						continue;
					}
					int newValue = dp[i & ~(1 << curSend)][prevEnd] + connect[prevEnd][from[curSend]] +
					               connect[from[curSend]][to[curSend]];
					dp[i][to[curSend]] = Math.min(dp[i][to[curSend]], newValue); 
				}
			}
		}
		int[] minBack = new int[mask + 1];
		for (int i = 0; i <= mask; ++i) {
			minBack[i] = Integer.MAX_VALUE;
			for (int j = 0; j < n; ++j) {
				if (dp[i][j] == Integer.MAX_VALUE) {
					continue;
				}
				minBack[i] = Math.min(minBack[i], dp[i][j] + connect[j][0]);
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= mask; ++i) {
			int cur = Math.max(minBack[i], minBack[mask & ~i]);
			result = Math.min(result, cur);
		}
		return result;
	}
}