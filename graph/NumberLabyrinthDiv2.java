import java.util.*;

public class NumberLabyrinthDiv2 {
	public int getMinimumNumberOfMoves(String[] board, int r1, int c1, int r2, int c2, int K) {
		int m = board.length;
		int n = board[0].length();
		int[] dr = new int[]{-1, 1, 0, 0};
		int[] dc = new int[]{0, 0, -1, 1};
		// dp[i][j][k] is the min move to arrive at pos (i, j) with k fills left.
		int[][][] dp = new int[m][n][K + 1];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				for (int k = 0; k <= K; ++k) {
					dp[i][j][k] = -1;
				}
			}
		}
		dp[r1][c1][K] = 0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add((r1 << 16) | (c1 << 8) | K);
		while (!queue.isEmpty()) {
			int value = queue.poll();
			int r = value >> 16;
			int c = (value >> 8) & 0xff;
			int k = value & 0xff;
			int level = dp[r][c][k];
			if (board[r].charAt(c) == '.') {
				if (k == 0) {
					continue;
				}
				for (int jump = 1; jump <= 9; ++jump) {
					for (int d = 0; d < 4; ++d) {
						int nr = r + dr[d] * jump;
						int nc = c + dc[d] * jump;
						int nk = k - 1;
						if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
							continue;
						}
						if (dp[nr][nc][nk] == -1) {
							if (nr == r2 && nc == c2) {
								return level + 1;
							}
							dp[nr][nc][nk] = level + 1;
							queue.add((nr << 16) | (nc << 8) | nk);
						}
					}
				}
			} else {
				int jump = board[r].charAt(c) - '0';
				if (jump == 0) {
					continue;
				}
				for (int d = 0; d < 4; ++d) {
					int nr = r + dr[d] * jump;
					int nc = c + dc[d] * jump;
					if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
						continue;
					}
					if (dp[nr][nc][k] == -1) {
						if (nr == r2 && nc == c2) {
							return level + 1;
						}
						dp[nr][nc][k] = level + 1;
						queue.add((nr << 16) | (nc << 8) | k);
					}
				}
			}
		}
		return -1;
	}
}
