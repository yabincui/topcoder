public class PrinceXToastbook {
	public double eat(int[] prerequisite) {
		int n = prerequisite.length;
		
		boolean[] visited = new boolean[n];
		int[] depCount = new int[n];
		while (true) {
			boolean updated = false;
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && (prerequisite[i] == -1 || visited[prerequisite[i]])) {
					updated = true;
					visited[i] = true;
					depCount[i] = (prerequisite[i] == -1 ? 0 : depCount[prerequisite[i]]+1);
				}
			}
			if (!updated) break;
		}
		
		// dp[i][j] is the possibility of understanding book j <= pos i.
		double[][] dp = new double[n+1][n];
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < n; ++j) {
				int prev = prerequisite[j];
				dp[i][j] = dp[i-1][j];
				if (prev == -1) {
					dp[i][j] += 1.0 / n;
				} else {
					dp[i][j] += 1.0 / (n - 1 - depCount[prev]) * dp[i-1][prev];
				}
				//System.out.printf("dp[%d][%d] = %f\n", i, j, dp[i][j]);
			}
		}
		double result = 0;
		for (int i = 0; i < n; ++i) {
			result += dp[n][i];
		}
		return result;
	}
}
