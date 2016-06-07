import java.util.*;
import java.math.*;

public class Polygons2 {
  public long number(int[] segments, int K) {
    Arrays.sort(segments);
    int n = segments.length;

    long[][] C = buildC(n, K);

    int maxValue = segments[segments.length - 1];
    // ways[i][j][k] means how many ways to select j numbers from segments[0..i] to get a sum >= k.
    long[][][] ways = new long[n][K + 1][maxValue + 2];
    for (int i = 0; i < n; ++i) {
      // No requirement of sum.
      for (int j = 0; j <= Math.min(i + 1, K); ++j) {
        ways[i][j][0] = C[i + 1][j];
      }
      for (int j = 1; j <= Math.min(i + 1, K); ++j) {
        for (int k = 1; k <= maxValue + 1; ++k) {
          if (i >= j) {
            ways[i][j][k] = ways[i-1][j][k];
          }
          int lastK = k - segments[i];
          if (lastK < 0) {
            lastK = 0;
          }
          if (i > 0) {
            ways[i][j][k] += ways[i-1][j-1][lastK];
          } else if (lastK == 0) {
            ways[i][j][k]++;
          }
          if (ways[i][j][k] == 0) {
            break;
          }
        }
      }
    }
    long result = 0;
    for (int i = K - 1; i < n; ++i) {
      result += ways[i - 1][K - 1][segments[i] + 1];
    }
    return result;
  }

  private long[][] buildC(int n, int m) {
    long[][] C = new long[n + 1][m + 1];
    C[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      C[i][0] = 1;
      if (i <= m) {
        C[i][i] = 1;
      }
      for (int j = 1; j <= m && j < i; ++j) {
        C[i][j] = C[i-1][j] + C[i-1][j-1];
      }
    }
    return C;
  }
}
