public class Piglets {
	public int choose(String trough) {
		if (trough.charAt(0) == '-') {
			return 0;
		}
		if (trough.charAt(trough.length() - 1) == '-') {
			return trough.length() - 1;
		}
			
		int n = trough.length();
		int mask = (1 << n) - 1;
		// dp[i][j] is the delay for pos j becomes sanwitched with mask i empty.
		int[][] dp = new int[mask + 1][n];
		for (int i = 1; i <= mask; ++i) {
			int maxDelayPos = -1;
			int maxDelay = -1;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					// If we choose pos j, when will it be sanwitched?
					int next = i & ~(1 << j);
					int delay = dp[next][j];
					if (maxDelay < delay) {
						maxDelay = delay;
						maxDelayPos = j;
					}
				}
			}
			int next = i & ~(1 << maxDelayPos);
			for (int j = 1; j < n-1; ++j) {
				if ((i & (1 << j)) != 0) {
					continue;
				}
				if ((i & (1 << (j-1))) == 0 && (i & (1 << (j+1))) == 0) {
					continue;
				}
				if ((next & (1 << (j-1))) == 0 && (next & (1 << (j+1))) == 0) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = dp[next][j] + 1;
			}
		}
		int initMask = 0;
		for (int i = 0; i < n; ++i) {
			if (trough.charAt(i) == '-') {
				initMask |= 1 << i;
			}
		}
		int maxDelay = -1;
		int maxDelayPos = -1;
		for (int i = 0; i < n; ++i) {
			if ((initMask & (1 << i)) != 0) {
				int next = initMask & ~(1 << i);
				int delay = dp[next][i];
				if (maxDelay < delay) {
					maxDelay = delay;
					maxDelayPos = i;
				}
			}
		}
		return maxDelayPos;
	}
}
