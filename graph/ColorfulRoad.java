public class ColorfulRoad {
	public int getMin(String road) {
		int n = road.length();
		// dp[i] is the min cost arriving at i.
		int[] dp = new int[n];
		for (int i = 0; i < n; ++i) {
			dp[i] = -1;
		}
		dp[0] = 0;
		for (int i = 1; i < n; ++i) {
			char prev;
			if (road.charAt(i) == 'R') {
				prev = 'B';
			} else if (road.charAt(i) == 'G') {
				prev = 'R';
			} else {
				prev = 'G';
			}
			for (int j = 0; j < i; ++j) {
				if (road.charAt(j) == prev && dp[j] != -1) {
					int tmp = dp[j] + (i - j) * (i - j);
					if (dp[i] == -1 || dp[i] > tmp) {
						dp[i] = tmp;
					}
				}
			}
		}
		return dp[n-1];
	}
}
