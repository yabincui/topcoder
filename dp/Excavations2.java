import java.util.*;

public class Excavations2 {
  class Type {
    boolean valid;
    int type;
    int count;
  }

  public long count(int[] kind, int[] found, int K) {
    ArrayList<Type> types = new ArrayList<Type>();
    int n = kind.length;
    for (int i = 0; i < n; ++i) {
      boolean existed = false;
      for (int j = 0; j < types.size(); ++j) {
        if (types.get(j).type == kind[i]) {
          types.get(j).count++;
          existed = true;
          break;
        }
      }
      if (!existed) {
        Type type = new Type();
        type.type = kind[i];
        type.count = 1;
        type.valid = false;
        types.add(type);
      }
    }
    int m = types.size();
    for (int i = 0; i < found.length; ++i) {
      for (int j = 0; j < m; ++j) {
        if (types.get(j).type == found[i]) {
          types.get(j).valid = true;
          break;
        }
      }
    }
    // dp[i][j] means the number of ways using types[0..i - 1] to enumerate j values.
    long[][] dp = new long[m + 1][K + 1];
    long[][] C = getCMatrix(n);
    
    dp[0][0] = 1;
    for (int i = 1; i <= m; ++i) {
      if (types.get(i - 1).valid == false) {
        for (int j = 0; j <= K; ++j) {
          dp[i][j] = dp[i - 1][j];
        }
      } else {
        for (int j = 1; j <= K; ++j) {
          for (int t = 1; t <= types.get(i - 1).count && t <= j; ++t) {
            dp[i][j] += dp[i - 1][j - t] * C[types.get(i - 1).count][t];
          }
        }
      }
    }
    return dp[m][K];
  }

  public long[][] getCMatrix(int n) {
    long[][] m = new long[n + 1][n + 1];
    for (int i = 0; i <= n; ++i) {
      m[i][0] = 1;
    }
    for (int i = 1; i <= n; ++i) {
      m[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        m[i][j] = m[i - 1][j] + m[i - 1][j - 1];
      }
    }
    return m;
  }
}
