import java.util.*;

public class NumberSplit {
	public long longestSequence(int start) {
		// dp[i] is the longest sequence can get from i.
		int[] dp = new int[start + 1];
		for (int i = 0; i <= Math.min(start, 9); ++i) {
			dp[i] = 1;
		}
		for (int i = 10; i <= start; ++i) {
			String s = Integer.valueOf(i).toString();
			dp[i] = findMax(0, 1, s, dp) + 1;
		}
		return dp[start];
	}
	
	private int findMax(int curPos, int mul, String s, int[] dp) {
		if (curPos == s.length()) {
			return dp[mul];
		}
		int curMax = 0;
		int value = 0;
		for (int end = curPos; end < s.length(); ++end) {
			value = value * 10 + s.charAt(end) - '0';
			int result = findMax(end + 1, mul * value, s, dp);
			curMax = Math.max(curMax, result);
		}
		return curMax;
	}
}
