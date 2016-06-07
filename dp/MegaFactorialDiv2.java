public class MegaFactorialDiv2 {
  final long MOD = 1000000009L;
  public int countDivisors(int N, int K) {
    int[] primes = getPrimes(N);
    int primeCount = primes.length;
    // dp[i][j][] means the primes counts for j!i.
    long[][][] dp = new long[K + 1][N + 1][primeCount];

    for (int i = 1; i <= N; ++i) {
      int value = i;
      for (int j = 0; j < primeCount; ++j) {
        dp[0][i][j] = getPrimesForValue(value, primes[j]);
      }
    }
    for (int i = 1; i <= K; ++i) {
      for (int j = 1; j <= N; ++j) {
        for (int k = 0; k < primeCount; ++k) {
          dp[i][j][k] = (dp[i][j-1][k] + dp[i-1][j][k]) % MOD;
        }
      }
    }
    long result = 1;
    for (int k = 0; k < primeCount; ++k) {
      result = (result * (dp[K][N][k] + 1)) % MOD;
    }
    return (int)result;
  }

  long getPrimesForValue(int value, int prime) {
    int count = 0;
    while (value % prime == 0) {
      count++;
      value /= prime;
    }
    count %= MOD;
    return count;
  }

  int[] getPrimes(int n) {
    boolean[] valid = new boolean[n + 1];
    int count = 0;
    for (int i = 2; i <= n; ++i) {
      valid[i] = true;
      for (int j = 2; j < i; ++j) {
        if (i % j == 0) {
          valid[i] = false;
          break;
        }
      }
      if (valid[i]) {
        count++;
      }
    }
    int[] primes = new int[count];
    int j = 0;
    for (int i = 2; i <= n; ++i) {
      if (valid[i]) {
        primes[j++] = i;
      }
    }
    return primes;
  }
}
