import java.util.*;

public class InsertSort {
	public int calcMinimalCost(int[] theArray) {
		int maxValue = 0;
		int n = theArray.length;
		for (int i = 0; i < n; ++i) {
			maxValue = Math.max(maxValue, theArray[i]);
		}
		// dp[i] is the cost to move when min value right should >= i.
		int[] dp = new int[maxValue + 1];
		for (int i = 0; i < n; ++i) {
			int[] nextDp = new int[maxValue + 1];
			int minPrev = Integer.MAX_VALUE;
			int cur = theArray[i];
			for (int j = 0; j <= maxValue; ++j) {
				minPrev = Math.min(minPrev, dp[j]);
				if (cur < j) {
					// Move cur to left.
					nextDp[j] = minPrev + cur;
				} else if (cur == j) {
					// No need to move.
					nextDp[j] = minPrev;
				} else if (cur > j) {
					// Move cur to right in order to keep min requirement.
					nextDp[j] = minPrev + cur;
				}
			}
			dp = nextDp;
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= maxValue; ++i) {
			result = Math.min(result, dp[i]);
		}
		return result;
	}
}