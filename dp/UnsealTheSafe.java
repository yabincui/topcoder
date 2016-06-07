import java.util.*;

public class UnsealTheSafe {
	public long countPasswords(int N) {
		boolean[][] map = new boolean[10][10];
		map[1][2] = map[1][4] = true;
		map[2][1] = map[2][3] = map[2][5] = true;
		map[3][2] = map[3][6] = true;
		map[4][1] = map[4][5] = map[4][7] = true;
		map[5][2] = map[5][4] = map[5][6] = map[5][8] = true;
		map[6][3] = map[6][5] = map[6][9] = true;
		map[7][4] = map[7][8] = map[7][0] = true;
		map[8][5] = map[8][7] = map[8][9] = true;
		map[9][6] = map[9][8] = true;
		map[0][7] = true;
		
		// dp[i][j] is how many ways to type when password left i length, and the first character should be j.
		long[][] dp = new long[N+1][10];
		for (int digit = 0; digit < 10; ++digit) {
			dp[1][digit] = 1;
		}
		for (int i = 2; i <= N; ++i) {
			for (int digit = 0; digit < 10; ++digit) {
				long count = 0;
				for (int nextDigit = 0; nextDigit < 10; ++nextDigit) {
					if (!map[digit][nextDigit]) {
						continue;
					}
					count += dp[i-1][nextDigit];
				}
				dp[i][digit] = count;
			}
		}
		long result = 0;
		for (int digit = 0; digit < 10; ++digit) {
			result += dp[N][digit];
		}
		return result;
	}
}