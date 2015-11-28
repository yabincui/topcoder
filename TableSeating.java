public class TableSeating {
	public double getExpected(int numTables, int[] probs) {
		int mask = (1 << numTables) - 1;
		// dp[i] is the expected tables used before turned away, if currently available table mask is i.
		double[] dp = new double[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			double value = 0.0;
			for (int j = 0; j < probs.length; ++j) {
				int curUse = j + 1;
				int count = 0;
				double sum = 0.0;
				for (int start = 0; start <= numTables - curUse; ++start) {
					int end = start + curUse - 1;
					int useMask = (1 << (end + 1)) - (1 << start);
					if ((i & useMask) != useMask) {
						continue;
					}
					int prev = i & ~useMask;
					count += 1;
					sum += dp[prev];
				}
				if (count != 0) {
					double add = curUse + (sum / count);
					value += probs[j] * add / 100;
				}
			}
			dp[i] = value;
		}
		return dp[mask];
	}
}
