import java.util.*;

public class IncreasingSubsequences {
	public long count(int[] a) {
		int n = a.length;
		long[] dp = new long[n];
		dp[0] = 1;
		for (int i = 1; i < n; ++i) {
			int nearestLower = -1;
			for (int j = i - 1; j >= 0; --j) {
				if (a[j] > a[i]) {
					continue;
				} else if (a[j] <= nearestLower) {
					continue;
				} else {
					dp[i] += dp[j];
					nearestLower = a[j];
				}
			}
			if (nearestLower == -1) {
				dp[i] = 1;
			}
		}
		long result = 0;
		int lastMax = -1;
		for (int i = n - 1; i >= 0; --i) {
			if (a[i] <= lastMax) {
				continue;
			}
			lastMax = Math.max(a[i], lastMax);
			result += dp[i];
		}
		return result;
	}
}