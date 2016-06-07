import java.util.*;

public class PolygonColors {
	
	static final long MOD = 100000007;
	
	public int getWays(int N, int[] colors) {
		for (int i = 0; i < N; ++i) {
			int j = (i + 1) % N;
			if (colors[i] == colors[j]) {
				return 0;
			}
		}
		
		// dp[i][j][k] means the maximum ways to draw diagonal in polygon that can only draw between i, j..k.
		// line[i][j] is connected, and is the leftest line in the polygon.
		long[][][] dp = new long[N][N][N];
		
		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				if (colors[i] == colors[j]) {
					dp[i][j][j] = 0;
				} else {
					dp[i][j][j] = 1;
				}
			}
		}
		
		for (int diff = 1; diff <= N; ++diff) {
			for (int i = 0; i < N; ++i) {
				for (int j = i + 1; j < N - diff; ++j) {
					int k = j + diff;
					// vertex i doesn't connect with j+1..k.
					long value = dp[j][j+1][k];
					for (int t = j+1; t < k; ++t) {
						if (colors[i] == colors[t]) {
							continue;
						}
						// vertex i connects with t.
						long left = dp[j][j+1][t];
						long right = dp[i][t][k];
						value = (value + left * right % MOD) % MOD;
					}
					
					// vertex i connects with k.
					if (!isNeighbor(i, k, N) && colors[i] != colors[k]) {
						value = (value + dp[j][j+1][k]) % MOD;
					}
					dp[i][j][k] = value;
				}
			}
		}
		return (int)dp[0][1][N - 1];
		
	}
	
	boolean isNeighbor(int i, int j, int N) {
		return ((j + 1) % N == i);
	}
}