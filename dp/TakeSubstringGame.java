import java.util.*;

public class TakeSubstringGame {
	public int winningMove(int n) {
		boolean[] dp = new boolean[n+1];
		for (int i = 0; i < 10 && i <= n; ++i) {
			dp[i] = false;
		}
		int result = -1;
		for (int i = 10; i <= n; ++i) {
			String s = Integer.valueOf(i).toString();
			boolean canWin = false;
			int minSub = Integer.MAX_VALUE;
			for (int start = 0; start < s.length(); ++start) {
				if (s.charAt(start) == '0') {
					continue;
				}
				int value = 0;
				for (int end = start; end < s.length(); ++end) {
					value = value * 10 + s.charAt(end) - '0';
					if (i != value && !dp[i-value]) {
						canWin = true;
						minSub = Math.min(minSub, value);
						if (i != n) {
							break;
						}
					}
				}
				if (canWin && i != n) {
					break;
				}
			}
			dp[i] = canWin;
			if (i == n && canWin) {
				result = minSub;
			}
		}
		return result;
	}
}