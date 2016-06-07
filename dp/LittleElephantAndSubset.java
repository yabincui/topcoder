public class LittleElephantAndSubset {
  final long MOD = 1000000007L;
  public int getNumber(int N) {
    // dp[bitmask] means the subset count arrived at bitmask.
    long[] dp = new long[1024];
    dp[0] = 1;
    enumerate(0, 9, 0, 0, dp, N);
    long result = 0;
    for (int i = 1; i < 1024; ++i) {
      result = (result + dp[i]) % MOD;
    }
    return (int)result;
  }

  void enumerate(int bit, int maxBit, long value, int bitmask, long[] dp, int N) {
    int start = (bit == 0) ? 1 : 0;
    for (int i = start; i <= 9; ++i) {
      if ((bitmask & (1 << i)) != 0) {
        continue;
      }
      long nextValue = value * 10 + i;
      if (nextValue > N) {
        continue;
      }
      // System.out.printf("nextValue = %d\n", nextValue);
      int newBitmask = bitmask | (1 << i);
      for (int mask = 0; mask < 1024; ++mask) {
        if ((mask & newBitmask) != 0) {
          continue;
        }
        dp[(mask | newBitmask)] = (dp[(mask | newBitmask)] + dp[mask]) % MOD;
      }

      if (bit < maxBit) {
        enumerate(bit + 1, maxBit, nextValue, newBitmask, dp, N);
      }
    }
  }

  int pow(int a, int b) {
    int result = 1;
    while (b-- > 0) {
      result *= a;
    }
    return result;
  }
}
