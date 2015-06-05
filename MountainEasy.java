public class MountainEasy {
  final long MOD = 1000000009L;
  public int countPlacements(String[] picture, int N) {
    int H = picture.length;
    int W = picture[0].length();

    boolean[][] needPaint = new boolean[H][W];
    for (int i = 0; i < H; ++i) {
      for (int j = 0; j < W; ++j) {
        if (picture[H - 1 - i].charAt(j) == 'X') {
          needPaint[i][j] = true;
        }
      }
    }
    int necessaryCount = 0;
    int unnecessaryCount = 0;
    boolean[][] painted = new boolean[H][W];
    for (int i = H - 1; i >= 0; --i) {
      for (int j = 0; j < W; ++j) {
        if (needPaint[i][j]) {
          if (!painted[i][j]) {
            necessaryCount++;
            paintMountain(i, j, painted);
          } else {
            unnecessaryCount++;
          }
        }
      }
    }

    long[][] C = buildSelectMatrix(N);

    long[][] necessaryDp = new long[necessaryCount + 1][N + 1];
    necessaryDp[0][0] = 1;
    for (int i = 1; i <= necessaryCount; ++i) {
      for (int j = 1; j <= N; ++j) {
        long count = 0;
        for (int k = 1; k <= j; ++k) {
          long temp = (necessaryDp[i - 1][j - k] * C[j][k]) % MOD;
          count += temp;
        }
        necessaryDp[i][j] = count;
      }
    }

    long[][] unnecessaryDp = new long[unnecessaryCount + 1][N + 1];
    for (int i = 0; i <= unnecessaryCount; ++i) {
      unnecessaryDp[i][0] = 1;
    }
    for (int i = 1; i <= unnecessaryCount; ++i) {
      for (int j = 1; j <= N; ++j) {
        long count = 0;
        for (int k = 0; k <= j; ++k) {
          long temp = (unnecessaryDp[i - 1][j - k] * C[j][k]) % MOD;
          count = (count + temp) % MOD;
        }
        unnecessaryDp[i][j] = count;
      }
    }

    long result = 0;
    for (int split = 0; split <= N; ++split) {
      long temp = (necessaryDp[necessaryCount][split] * unnecessaryDp[unnecessaryCount][N - split]) % MOD;
      temp = (temp * C[N][split]) % MOD;
      result = (result + temp) % MOD;
    }
    return (int)result;
  }

  long[][] buildSelectMatrix(int n) {
    long[][] C = new long[n + 1][n + 1];
    for (int i = 1; i <= n; ++i) {
      C[i][0] = 1;
      C[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
      }
    }
    return C;
  }

  void paintMountain(int y, int x, boolean[][] painted) {
    int H = painted.length;
    int W = painted[0].length;
    for (int i = y; i >= 0; --i) {
      for (int j = Math.max(0, x - (y - i)); j <= Math.min(W - 1, x + (y - i)); ++j) {
        painted[i][j] = true;
      }
    }
  }
}
