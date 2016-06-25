public class LinenCenterEasy {
	public int countStrings(String S, int N, int K) {
		int L = S.length();
		// prev_match[i] means S[0..i] has a prefix S[0..prev_match[i]-1].
		int[] prev_match = new int[L];
		int k = 0;
		for (int i = 1; i < L; ++i) {
			while (S.charAt(i) != S.charAt(k) && k != 0) {
				k = prev_match[k-1];
			}
			if (S.charAt(i) == S.charAt(k)) {
				k++;
			}
			prev_match[i] = k;
      //System.out.printf("prev_match[%d] = %d\n", i, k);
		}
		
		// transfer[i][j] is at pos i, if meeting j + 'a', which pos should transfer to.
		// at pos 0 means matching nothing, at pos i means matching S[0..i-1] already.
		int[][] transfer = new int[L][26];
		for (int i = 0; i < L; ++i) {
			for (int j = 0; j < 26; ++j) {
				char c = (char)(j + 'a');
				if (c == S.charAt(i)) {
					transfer[i][j] = i + 1;
					//System.out.printf("transfer[%d][%d] = %d\n", i, j, transfer[i][j]);
					continue;
				}
				k = i;
				while (k != 0) {
					k = prev_match[k-1];
					if (c == S.charAt(k)) {
						transfer[i][j] = k + 1;
						break;
					}
				}
				/*
				if (transfer[i][j] != 0) {
					System.out.printf("transfer[%d][%d] = %d\n", i, j, transfer[i][j]);
				}
				*/
			}
		}
		// dp[i][j] is the count of strings matched i S, and the i+1'th S has matched j characters.
		int[][] dp = new int[K + 1][L];
		int[][] next_dp = new int[K + 1][L];
		dp[0][0] = 1;
		int result = 0;
		int MOD = 1000000009;
		if (L * K == 0) {
			result++;
		}
		for (int step = 1; step <= L * K + N; ++step) {
			for (int i = 0; i <= K; ++i) {
				for (int j = 0; j < L; ++j) {
					if (dp[i][j] == 0) continue;
					for (int c = 0; c < 26; ++c) {
						int ni = i;
						int nj = transfer[j][c];
						if (nj == L) {
							ni++; nj = 0;
						}
						if (ni <= K) {
							next_dp[ni][nj] += dp[i][j];
							if (next_dp[ni][nj] >= MOD) {
								next_dp[ni][nj] -= MOD;
							}
						}
					}
				}
			}
			for (int i = 0; i <= K; ++i) {
				for (int j = 0; j < L; ++j) {
					dp[i][j] = next_dp[i][j];
					next_dp[i][j] = 0;
				}
			}
			if (step >= L * K) {
				for (int i = 0; i < L; ++i) {
					if (dp[K][i] != 0) {
						result = result + dp[K][i];
						//System.out.printf("step = %d, dp[%d][%d] = %d, result = %d\n",
						//		step, K, i, dp[K][i], result);
						if (result >= MOD) {
							result -= MOD;
						}
					}
				}
			}
		}
		return result;
	}

  public static void main(String[] args) {
    LinenCenterEasy obj = new LinenCenterEasy();
    String s = "yxyzxxzxyzxzxxxyyxxxzyxzxxzzzxzzyxxxzxyyxzxzyzy";
    int result = obj.countStrings(s, 45, 26);
    System.out.printf("result = %d, correct = %d\n", result, 503385394);
  }
}
