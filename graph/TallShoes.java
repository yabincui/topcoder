public class TallShoes {
	public int maxHeight(int N, int[] X, int[] Y, int[] height, long B) {
		int[][] connect = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				connect[i][j] = -1;
			}
		}
		for (int i = 0; i < X.length; ++i) {
			int a = X[i];
			int b = Y[i];
			if (connect[a][b] == -1 || connect[a][b] > height[i]) {
				connect[a][b] = connect[b][a] = height[i];
			}
		}
		int low = 0;
		int high = 1000000000;
		while (low + 1 < high) {
			int mid = (low + high) / 2;
			if (fulfill(mid, N, connect, B)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		if (high == low + 1 && fulfill(high, N, connect, B)) {
			return high;
		}
		return low;
	}
	
	private boolean fulfill(int h, int N, int[][] connect, long B) {
		// dp[i] is the least cost for arriving pos i.
		long[] dp = new long[N];
		for (int i = 0; i < N; ++i) {
			dp[i] = B + 1;
		}
		dp[0] = 0;
		boolean[] visited = new boolean[N];
		while (true) {
			int sel = -1;
			for (int i = 0; i < N; ++i) {
				if (!visited[i] && dp[i] <= B) {
					if (sel == -1 || dp[sel] > dp[i]) {
						sel = i;
					}
				}
			}
			if (sel == -1) {
				break;
			}
			if (sel == N - 1) {
				return true;
			}
			visited[sel] = true;
			for (int i = 0; i < N; ++i) {
				if (!visited[i] && connect[sel][i] != -1) {
					int limit = connect[sel][i];
					long cost = 0;
					if (limit < h) {
						cost = (long)(h - limit) * (h - limit);
					}
					if (dp[i] > cost + dp[sel]) {
						dp[i] = cost + dp[sel];
					}
				}
			}
		}
		return false;
	}
}
