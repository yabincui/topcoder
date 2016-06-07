import java.util.*;

public class BoggleScore {
	public long bestScore(String[] grid, String[] words) {
		final long MOD = 10000000000000L;
		long result = 0;
		int[] dr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dc = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
		for (String word : words) {
			int rows = grid.length;
			int cols = grid[0].length();
			int n = word.length();
			// dp[i][j] is the number of ways to match words[0..i-1] with last charcter at pos j.
			long[][] dp = new long[n][rows * cols];
			for (int r = 0; r < rows; ++r) {
				for (int c = 0; c < cols; ++c) {
					if (word.charAt(0) == grid[r].charAt(c)) {
						dp[0][r * cols + c] = 1;
					}
				}
			}
			for (int i = 1; i < n; ++i) {
				for (int r = 0; r < rows; ++r) {
					for (int c = 0; c < cols; ++c) {
						int pos = r * cols + c;
						if (word.charAt(i) != grid[r].charAt(c)) {
							continue;
						}
						for (int k = 0; k < dr.length; ++k) {
							int nr = r + dr[k];
							int nc = c + dc[k];
							if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
								continue;
							}
							int npos = nr * cols + nc;
							dp[i][pos] = (dp[i][pos] + dp[i-1][npos]) % MOD; 
						}
					}
				}
			}
			long count = 0;
			for (int i = 0; i < rows * cols; ++i) {
				count = (count + dp[n-1][i]) % MOD;
			}
			long score = (count * word.length() * word.length()) % MOD;
			result = (result + score) % MOD;
		}
		return result;
	}
}