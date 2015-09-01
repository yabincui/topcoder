import java.util.*;

public class NumbersAndMatches {
	public long differentNumbers(long N, int K) {
		int[] digitBits = new int[]{
				63,  // 0
				56,  // 1
				109, // 2
				121, // 3
				114, // 4
				91,  // 5
				95,  // 6
				49,  // 7
				127, // 8
				123, // 9
		};
		int[] digitCount = new int[10];
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 7; ++j) {
				if ((digitBits[i] & (1 << j)) != 0) {
					digitCount[i]++;
				}
			}
		}
		int[][] digitDiff = new int[10][10];
		for (int i = 0; i < 10; ++i) {
			for (int j = i; j < 10; ++j) {
				int sameCount = 0;
				for (int k = 0; k < 7; ++k) {
					if ((digitBits[i] & (1 << k)) == (digitBits[j] & (1 << k)) &&
							((digitBits[i] & (1 << k)) != 0)) {
						sameCount++;
					}
				}
				digitDiff[i][j] = digitDiff[j][i] = digitCount[i] + digitCount[j] - 2 * sameCount;
			}
		}
		ArrayList<Integer> digits = new ArrayList<Integer>();
		long tmp = N;
		do {
			digits.add((int)(tmp % 10));
			tmp /= 10;
		} while (tmp != 0);
		int levels = digits.size();
		int maxMoves = levels * 7;
		int doubleMoves = maxMoves * 2;
		int changeMiddle = maxMoves;
		int maxChange = changeMiddle + maxMoves;
		// dp[i][j][k] means how many ways to form 0..i-1 digits by
		// using j moves and (k - changeMiddle) more matches.
		long[][][] dp = new long[levels + 1][doubleMoves + 1][maxChange + 1];
		dp[0][0][changeMiddle] = 1;
		for (int i = 0; i < levels; ++i) {
			for (int j = 0; j <= doubleMoves; ++j) {
				for (int k = 0; k <= maxChange; ++k) {
					if (dp[i][j][k] == 0) {
						continue;
					}
					for (int t = 0; t < 10; ++t) {
						int addMove = digitDiff[digits.get(i)][t];
						int addChange = digitCount[t] - digitCount[digits.get(i)];
						dp[i+1][j + addMove][k + addChange] += dp[i][j][k];
					}
				}
			}
		}
		long result = 0;
		for (int i = 0; i <= maxMoves && i <= 2 * K; ++i) {
			result += dp[levels][i][changeMiddle];
		}
		
		return result;
	}
}