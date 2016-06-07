import java.util.*;

public class SameDigits {
	public int howMany(int n, int k) {
		if (n < k) {
			return 0;
		}
		final int MOD = 44444444;
		// dpLessThanK[i][j] is the ways filling first i digits, with last digit j. Max repetition is < k.
		int[][] dpLessThanK = new int[n+1][10];
		// dpEqualK[i][j] is the ways filling first i digits, with last digit j. Max repetition is == k.
		int[][] dpEqualK = new int[n+1][10];
		
		// Init with first digit.
		for (int digit = 1; digit <= 9; ++digit) {
			for (int len = 1; len < k; ++len) {
				dpLessThanK[len][digit] = 1;
			}
			dpEqualK[k][digit] = 1;
		}
		for (int i = 2; i <= n; ++i) {
			for (int len = 1; len <= Math.min(i-1, k-1); ++len) {
				for (int digit = 0; digit <= 9; ++digit) {
					long prevSum = 0;
					for (int prevDigit = 0; prevDigit <= 9; ++prevDigit) {
						if (digit == prevDigit) {
							continue;
						}
						prevSum += dpLessThanK[i-len][prevDigit];
					}
					dpLessThanK[i][digit] = (int)((dpLessThanK[i][digit] + prevSum) % MOD);
				}
			}
			for (int len = 1; len <= Math.min(i-1, k); ++len) {
				for (int digit = 0; digit <= 9; ++digit) {
					long prevSum = 0;
					for (int prevDigit = 0; prevDigit <= 9; ++prevDigit) {
						if (digit == prevDigit) {
							continue;
						}
						prevSum += dpEqualK[i-len][prevDigit];
					}
					
					dpEqualK[i][digit] = (int)((dpEqualK[i][digit] + prevSum) % MOD);
					//System.out.printf("dpEqualK[%d][%d] = %d\n", i, digit, dpEqualK[i][digit]);
				}
			}
			// From dpLessThanK to dpEqualK.
			if (i > k) {
				int len = k;
				for (int digit = 0; digit <= 9; ++digit) {
					long prevSum = 0;
					for (int prevDigit = 0; prevDigit <= 9; ++prevDigit) {
						if (digit == prevDigit) {
							continue;
						}
						prevSum += dpLessThanK[i-len][prevDigit];
					}
					dpEqualK[i][digit] = (int)((dpEqualK[i][digit] + prevSum) % MOD);
				}
			}
		}
		int result = 0;
		for (int i = 1; i <= n; ++i) {
			for (int digit = 0; digit <= 9; ++digit) {
				result = (result + dpEqualK[i][digit]) % MOD;
			}
		}
		return result;
	}
}