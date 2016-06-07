import java.util.*;

public class TheSumOfLuckyNumbers {
	public int[] sum(int n) {
		ArrayList<Integer> luckyNumbers = new ArrayList<Integer>();
		luckyNumbers.add(4);
		luckyNumbers.add(7);
		int start = 0;
		while (true) {
			boolean exceedN = false;
			int end = luckyNumbers.size();
			for (int i = start; i < end; ++i) {
				int cur = luckyNumbers.get(i);
				for (int add = 4; add <= 7; add += 3) {
					int next = cur * 10 + add;
					if (next > n) {
						exceedN = true;
						break;
					}
					luckyNumbers.add(next);
				}
				if (exceedN) break;
			}
			if (exceedN) break;
			start = end;
		}
		
		int[] dp = new int[n + 1];
		int[] prev = new int[n + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < luckyNumbers.size(); ++j) {
				int sub = luckyNumbers.get(j);
				if (sub > i) {
					break;
				}
				if (sub == i) {
					dp[i] = 1;
					prev[i] = 0;
				} else if (dp[i-sub] != 0) {
					int newCount = dp[i-sub] + 1;
					if (dp[i] == 0 || newCount < dp[i]) {
						dp[i] = newCount;
						prev[i] = i - sub;
					}
				}
			}
		}
		int[] result = new int[dp[n]];
		int cur = n;
		for (int i = 0; i < dp[n]; ++i) {
			result[i] = cur - prev[cur];
			cur = prev[cur];
		}
		return result;
	}
}