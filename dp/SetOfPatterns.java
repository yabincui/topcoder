import java.util.*;

public class SetOfPatterns {
	public int howMany(String[] patterns, int k) {
		final int MOD = 1000003;
		int m = patterns[0].length();
		int n = patterns.length;
		int mask = (1 << n) - 1;
		int[][] dp = new int[m][mask + 1];
		for (char c = 'a'; c <= 'z'; ++c) {
			int bits = 0;
			for (int i = 0; i < n; ++i) {
				if (patterns[i].charAt(0) == '?' || patterns[i].charAt(0) == c) {
					bits |= (1 << i);
				}
			}
			dp[0][bits]++;
		}
		for (int pos = 1; pos < m; ++pos) {
			for (char c = 'a'; c <= 'z'; ++c) {
				int bits = 0;
				for (int i = 0; i < n; ++i) {
					if (patterns[i].charAt(pos) == '?' || patterns[i].charAt(pos) == c) {
						bits |= (1 << i);
					}
				}
				for (int i = 0; i <= mask; ++i) {
					int newBits = i & bits;
					dp[pos][newBits] = (dp[pos][newBits] + dp[pos-1][i]) % MOD;
				}
			}
		}
		int result = 0;
		for (int i = 0; i <= mask; ++i) {
			if (getBits(i, n) == k) {
				result = (result + dp[m-1][i]) % MOD;
			}
		}
		return result;
	}
	
	int getBits(int mask, int n) {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			if ((mask & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}
}