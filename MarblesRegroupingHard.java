import java.util.*;

public class MarblesRegroupingHard {
	class State {
	}
	
	public int minMoves(String[] boxes) {
		int m = boxes.length;
		int n = boxes[0].split(" ").length;
		// board[i][j] is the number of items of color j in box i.
		int[][] board = new int[m][n];
		for (int i = 0; i < m; ++i) {
			String[] strs = boxes[i].split(" ");
			for (int j = 0; j < n; ++j) {
				board[i][j] = Integer.valueOf(strs[j]);
			}
		}
		int mask = (1 << n) - 1;
		// dp[i][j] is the max items that can retain in the same box after processing i boxes, with j color settled.
		int[][] dp = new int[m][mask+1];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j <= mask; ++j) {
				dp[i][j] = -1;
			}
		}
		dp[0][0] = 0;
		for (int i = 0; i < n; ++i) {
			dp[0][(1 << i)] = board[0][i];
		}
		for (int i = 1; i < m; ++i) {
			// Don't settle anything.
			for (int j = 0; j <= mask; ++j) {
				if (dp[i-1][j] == -1) {
					continue;
				}
				dp[i][j] = dp[i-1][j];
			}
			// Settle color j at box i.
			for (int j = 0; j < n; ++j) {
				for (int k = 0; k <= mask; ++k) {
					if ((k & (1 << j)) != 0) {
						continue;
					}
					if (dp[i-1][k] == -1) {
						continue;
					}
					dp[i][k | (1 << j)] = Math.max(dp[i][k | (1 << j)], dp[i-1][k] + board[i][j]);
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				sum += board[i][j];
			}
		}
		
		return sum - dp[m-1][mask];
	}
}