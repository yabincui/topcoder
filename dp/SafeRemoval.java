import java.util.*;

public class SafeRemoval {
  public int removeThem(int[] seq, int K) {
    int n = seq.length;
    // dp[i][j][k][t] is valid for i (%4 = 0) items, j (%4 = 1) items, k (%4 = 2) items, t (%4 = 3) items.
    boolean[][][][] dp = new boolean[n + 1][n + 1][n + 1][n + 1];
    int[] num = new int[4];
    for (int i = 0; i < n; ++i) {
      int t = seq[i] % 4;
      num[t]++;
    }
    dp[num[0]][num[1]][num[2]][num[3]] = true;
    for (int i = num[0]; i >= 0; --i) {
      for (int j = num[1]; j >= 0; --j) {
        for (int k = num[2]; k >= 0; --k) {
          for (int t = num[3]; t >= 0; --t) {
            if (dp[i][j][k][t] == false) {
              continue;
            }
            int curMod = (i * 0 + (j * 1) + (k * 2) + (t * 3)) % 4;
            for (int p = 0; p < n; ++p) {
              int mod = p % 4;
              if (mod == 0 && i == 0 || mod == 1 && j == 0 || mod == 2 && k == 0 || mod == 3 && t == 0) {
                continue;
              }
              int newMod = (curMod - mod + 4) % 4;
              if (newMod == 0) {
                continue;
              }
              if (mod == 0) {
                dp[i - 1][j][k][t] = true;
              } else if (mod == 1) {
                dp[i][j - 1][k][t] = true;
              } else if (mod == 2) {
                dp[i][j][k - 1][t] = true;
              } else if (mod == 3) {
                dp[i][j][k][t - 1] = true;
              }
            }
          }
        }
      }
    }
    Arrays.sort(seq);
    int[][] values = new int[4][n + 1];
    int[] pos = new int[4];
    for (int i = n - 1; i >= 0; --i) {
      int mod = seq[i] % 4;
      values[mod][pos[mod] + 1] = values[mod][pos[mod]] + seq[i];
      pos[mod]++;
    }
    int result = -1;
    for (int i = num[0]; i >= 0; --i) {
      for (int j = num[1]; j >= 0; --j) {
        for (int k = num[2]; k >= 0; --k) {
          for (int t = num[3]; t >= 0; --t) {
            if (!dp[i][j][k][t]) {
              continue;
            }
            int diffNum = num[0] - i + num[1] - j + num[2] - k + num[3] - t;
            if (diffNum == K) {
              int temp = values[0][i] + values[1][j] + values[2][k] + values[3][t];
              if (result == -1 || result < temp) {
                result = temp;
              }
            }
          }
        }
      }
    }
    return result;
  }
}
