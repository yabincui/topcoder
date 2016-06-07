public class CarelessSecretary {
	final static long MOD = 1000000007;
	public int howMany(int N, int K) {
		// sn[i] = i!
		long[] sn = new long[N + 1];
		sn[0] = 1;
		for (int i = 1; i <= N; ++i) {
			sn[i] = (sn[i - 1] * i) % MOD;
		}
		// cn[i][j] = C(i, j) = i!/(j! * (i - j)!)
		long[][] cn = generateC(K);
		long[] match = new long[K + 1];
		match[K] = sn[N - K];
		for (int i = K - 1; i > 0; --i) {
			int t = K - i;
			long sum = 0;
			for (int j = 0; j <= t; ++j) {
				sum += cn[t][j] * sn[N - i - j] % MOD * (j % 2 == 0 ? 1 : -1);
				sum = (sum + MOD) % MOD;
			}
			match[i] = (cn[K][K - i] * sum) % MOD;
		}
		long add = 0;
		for (int i = 1; i <= K; ++i) {
			add = (add + match[i]) % MOD;
		}
		long result = (sn[N] - add + MOD) % MOD;
		return (int)result;
	}
	
	long[][] generateC(int n) {
		long[][] m = new long[n + 1][n + 1];
		m[0][0] = 1;
		for (int i = 1; i <= n; ++i) {
			m[i][0] = m[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				m[i][j] = m[i - 1][j] + m[i - 1][j - 1];
			}
		}
		return m;
	}
}