public class PolyMove {
	public double addedArea(int[] x, int[] y) {
		int n = x.length;
		double[] increment = new double[n];
		for (int i = 0; i < n; ++i) {
			int prev = (i + n - 1) % n;
			int next = (i + 1) % n;
			double lineDist = getLineDist(x[prev], y[prev], x[next], y[next]);
			increment[i] = lineDist / 2.0;
		}
		double[] dp = new double[n+1];
		dp[0] = 0;
		dp[1] = increment[0];
		// Last doesn't move.
		for (int i = 2; i < n; ++i) {
			dp[i] = Math.max(dp[i-1], increment[i-1] + dp[i-2]);
		}
		double result = dp[n-1];
		dp[0] = 0;
		dp[1] = 0;
		// First doesn't move.
		for (int i = 2; i <= n; ++i) {
			dp[i] = Math.max(dp[i-1], increment[i-1] + dp[i-2]);
		}
		result = Math.max(result, dp[n]);
		return result;
	}
	
	private double getLineDist(int x1, int y1, int x2, int y2) {
		double dx = (x1 - x2) * (x1 - x2);
		double dy = (y1 - y2) * (y1 - y2);
		return Math.sqrt(dx + dy);
	}
}
