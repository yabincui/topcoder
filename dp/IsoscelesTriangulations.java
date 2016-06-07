import java.util.*;

// Using HashMap, creating objects costs not O(1). Using primitive array is the most efficient way.
public class IsoscelesTriangulations {
	boolean[][] visited;
	int[][][] dp;
	int n;
	int k;
	final int MOD = 123456789;
	
	public int getCount(int n, int k) {
		// dp[i] is the count of state i.
		this.n = n;
		this.k = k;
		this.dp = new int[n][n][k+1];
		this.visited = new boolean[n][n];
		getValue(1, n-1);
		return dp[1][n-1][k];
	}
	
	private void getValue(int b, int c) {
		if (visited[b][c]) {
			return;
		}
		visited[b][c] = true;
		if (b + 1 == c) {
			int isoscelesCount = isIsosceles(0, b, c);
			if (isoscelesCount <= k) {
				dp[b][c][isoscelesCount] = 1;
			}
		} else {
			// Connect state.a with [state.b+1, state.c-1].
			for (int t = b+1; t <= c-1; ++t) {
				// Connect state.a with t.
				// If we connect state.a with t, we can't connect state.a with [state.b+1, t-1]. Because we have
				// done that before. So we need to connect state.b with t.
				if (b + 1 != t) {
					int firstCount = isIsosceles(0, b, t);
					getValue(1, t-b);
					getValue(t, c);
					combineValue(1, t-b, t, c, b, c, firstCount);
				} else {
					int firstCount = isIsosceles(0, b, t);
					getValue(t, c);
					combineValue(t, c, b, c, firstCount);
				}
			}
			// Connect state.b with state.c.
			int firstCount = isIsosceles(0, b, c);
			getValue(1, c - b);
			combineValue(1, c-b, b, c, firstCount);
		}
	}
	
	void combineValue(int b1, int c1, int b2, int c2, int b, int c, int firstCount) {
		for (int i = 0; i <= k; ++i) {
			long sum = 0;
			for (int j = 0; j <= i - firstCount; ++j) {
				sum += (long)dp[b1][c1][j] * dp[b2][c2][i-firstCount-j];
			}
			dp[b][c][i] = (int)((dp[b][c][i] + sum) % MOD);
		}
	}
	
	void combineValue(int b1, int c1, int b, int c, int firstCount) {
		for (int i = firstCount; i <= k; ++i) {
			dp[b][c][i] = (dp[b][c][i] + dp[b1][c1][i-firstCount]) % MOD;
		}
	}
	
	private int isIsosceles(int a, int b, int c) {
		int distAB = (b - a);
		int distBC = (c - b);
		int distAC = (a + n - c);
		if (distAB == distBC || distAB == distAC || distBC == distAC) {
			return 1;
		}
		return 0;
	}
}