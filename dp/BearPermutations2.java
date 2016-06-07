public class BearPermutations2 {
	public int getSum(int N, int MOD) {
		int[][] C = buildC(N, MOD);
		long[] P = buildP(N, MOD);
		// dp[i][j] is the score sum of i numbers, with min value at pos j (0-i-1).
		long[][] dp = new long[N+1][N];
		for (int n = 3; n <= N; ++n) {
			for (int minPos = 0; minPos < n; ++minPos) {
				int leftCount = minPos;
				int rightCount = n - 1 - minPos;
				long select = C[n-1][leftCount];
				if (leftCount == 0) {
					long sum = 0;
					for (int i = 0; i < rightCount; ++i) {
						sum += dp[rightCount][i];
					}
					sum %= MOD;
					dp[n][minPos] = (select * sum) % MOD;
				} else if (rightCount == 0) {
					long sum = 0;
					for (int i = 0; i < leftCount; ++i) {
						sum += dp[leftCount][i];
					}
					sum %= MOD;
					dp[n][minPos] = (select * sum) % MOD;
				} else {
					long leftSum = 0;
					for (int i = 0; i < leftCount; ++i) {
						leftSum += dp[leftCount][i];
					}
					leftSum %= MOD;
					long rightSum = 0;
					for (int i = 0; i < rightCount; ++i) {
						rightSum += dp[rightCount][i];
					}
					rightSum %= MOD;
					long sum = 0;
					sum = (sum + leftSum * P[rightCount]) % MOD;
					sum = (sum + rightSum * P[leftCount]) % MOD;
					long combineSum = 0;
					for (int i = 0; i < leftCount; ++i) {
						for (int j = 0; j < rightCount; ++j) {
							combineSum += leftCount - i + j + 1;
						}
					}
					combineSum %= MOD;
					combineSum = combineSum * P[leftCount - 1] % MOD * P[rightCount - 1] % MOD;
					sum = (sum + combineSum) % MOD;
					dp[n][minPos] = (select * sum) % MOD;
				}
			}
		}
		long result = 0;
		for (int i = 0; i < N; ++i) {
			result += dp[N][i];
		}
		int ret = (int)(result % MOD);
		return ret;
	}
	
	private int[][] buildC(int N, int MOD) {
		int[][] C = new int[N+1][N+1];
		C[0][0] = 1;
		for (int i = 1; i <= N; ++i) {
			C[i][0] = C[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				C[i][j] = (C[i-1][j] + C[i-1][j-1]) % MOD;
			}
		}
		return C;
	}
	private long[] buildP(int N, int MOD) {
		long[] P = new long[N+1];
		P[0] = 1;
		for (int i = 1; i <= N; ++i) {
			P[i] = (P[i-1] * i) % MOD;
		}
		return P;
	}
}
