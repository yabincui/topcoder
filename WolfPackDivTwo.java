public class WolfPackDivTwo {
  final int MOD = 1000000007;
  public int calc(int[] x, int[] y, int m) {
    int n = x.length;
    int H = 151;
    int W = 151;
    int[][][] map = new int[n][H + 1][W + 1];
    for (int i = 0; i < n; ++i) {
      int[][] map1 = new int[H + 1][W + 1];
      map1[y[i] + 50][x[i] + 50] = 1;
      for (int j = 0; j < m; ++j) {
        int[][] map2 = new int[H + 1][W + 1];
        for (int h = 1; h < H; ++h) {
          for (int w = 1; w < W; ++w) {
            map2[h - 1][w] = (map2[h - 1][w] + map1[h][w]) % MOD;
            map2[h + 1][w] = (map2[h + 1][w] + map1[h][w]) % MOD;
            map2[h][w - 1] = (map2[h][w - 1] + map1[h][w]) % MOD;
            map2[h][w + 1] = (map2[h][w + 1] + map1[h][w]) % MOD;
          }
        }
        map1 = map2;
      }
      for (int h = 0; h <= H; ++h) {
        for (int w = 0; w <= W; ++w) {
          map[i][h][w] = map1[h][w];
        }
      }
    }
    int result = 0;
    for (int h = 0; h <= H; ++h) {
      for (int w = 0; w <= W; ++w) {
        long mul = 1;
        for (int i = 0; i < n; ++i) {
          mul = (mul * map[i][h][w]) % MOD;
        }
        result = (int)((result + mul) % MOD);
      }
    }
    return result;
  }
}
