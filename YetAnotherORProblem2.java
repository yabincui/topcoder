import java.util.*;

public class YetAnotherORProblem2 {
  final int MOD = 1000000009;
  long[][] C;

  public int countSequences(int N, int R) {
    int maxBits = 0;
    for (int level = 1; level <= R; level <<= 1, maxBits++) {
    }
    buildSelect(Math.max(N, maxBits));
    long result = 0;
    for (int zeroCount = 0; zeroCount <= N; ++zeroCount) {
      long selectZero = C[N][zeroCount];
      long temp = permute(N - zeroCount, R);
      long add = temp * selectZero;
      result = (result + add % MOD) % MOD;
    }
    return (int)result;
  }

  void buildSelect(int n) {
    C = new long[n+1][n+1];
    C[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      C[i][0] = C[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        C[i][j] = (C[i-1][j-1] + C[i-1][j]) % MOD;
      }
    }
  }

  long permute(int n, int maxValue) {
    if (n == 0) {
      return 1;
    }
    int maxBits = 0;
    for (int level = 1; level <= maxValue; level <<= 1, maxBits++) {
    }
    // dp[i][j] means how many ways to select i increasing non-zero Ax from j bits.
    long[][] dp = new long[n][maxBits + 1];
    for (int i = 0; i <= maxBits; ++i) {
      dp[0][i] = 1;
    }
    if (n > 1) {
      for (int i = 1; i <= maxBits; ++i) {
        int count = 1;
        for (int j = 0; j < i; ++j) {
          count *= 2;
        }
        dp[1][i] = count - 1;  // 2^i - 1.
      }
    }
    for (int i = 2; i < n; ++i) {
      for (int j = i; j <= maxBits; ++j) {
        dp[i][j] = dp[i][j-1];
        for (int k = 1; k <= j - (i - 1); ++k) {
          // One bit is the highest, the other k-1 bits can randomly selected.
          dp[i][j] = (dp[i][j] + (C[j-1][k-1] * dp[i-1][j-k])) % MOD;
        }
      }
    }

    long result = 0;
    // randomly select last value.
    for (int lastValue = 1; lastValue <= maxValue; ++lastValue) {
      int remBits = 0;
      for (int level = 1; level <= lastValue; level <<= 1) {
        if ((lastValue & level) == 0) {
          remBits++;
        }
      }
      result = (result + dp[n-1][remBits]) % MOD;
    }
    // n!
    for (int i = 2; i <= n; ++i) {
      result = (result * i) % MOD;
    }
    return result;
  }

  public int countSequences2(int N, int R) {
    ArrayList<Integer> array = new ArrayList<Integer>();
    int result = find(0, N, R, array);
    return result;
  }

  int find(int k, int N, int R, ArrayList<Integer> array) {
    if (k == N) {
      return 1;
    }
    int result = 0;
    for (int i = 0; i <= R; ++i) {
      boolean valid = true;
      for (int j = 0; j < k; ++j) {
        if ((array.get(j) & i) != 0) {
          valid = false;
          break;
        }
      }
      if (!valid) {
        continue;
      }
      array.add(i);
      result += find(k + 1, N, R, array);
      array.remove(k);
    }
    return result;
  }
}
