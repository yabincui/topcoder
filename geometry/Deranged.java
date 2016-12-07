import java.util.*;

public class Deranged {

	public long numDerangements(int[] nums) {
		int n = nums.length;
		int mask = (1 << n) - 1;
		// dp[i] means how to place mask i into first several slots.
		long[] dp = new long[mask + 1];
		dp[0] = 1;
		for (int cur = 1; cur <= mask; ++cur) {
			int count = getBitCount(cur);
			boolean[] used = new boolean[n];
			for (int j = 0; j < n; ++j) {
				if (((cur >> j) & 1) != 0 && nums[count - 1] != nums[j] && !used[nums[j]]) {
					dp[cur] += dp[cur & ~(1 << j)];
					used[nums[j]] = true;
				}
			}
			//System.out.printf("dp[%d] = %d\n", cur, dp[cur]);
		}
		return dp[mask];
	}
	
	int getBitCount(int mask) {
		int n = 0;
		while (mask != 0) {
			n++;
			mask &= mask - 1;
		}
		return n;
	}
}