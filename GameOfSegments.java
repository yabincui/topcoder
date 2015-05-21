// http://apps.topcoder.com/wiki/display/tc/SRM+624
// http://web.mit.edu/sp.268/www/nim.pdf
public class GameOfSegments {
  public int winner(int N) {
    int[] g = new int[N + 1];
    g[0] = 0;
    g[1] = 0;
    for (int n = 2; n <= N; ++n) {
      boolean[] hit = new boolean[N + 1];
      for (int i = 0; i <= n - 2 - i; ++i) {
        int temp = g[i] ^ g[n - 2 - i];
        hit[temp] = true;
      }
      for (int i = 0; i <= N; ++i) {
        if (hit[i] == false) {
          g[n] = i;
          break;
        }
      }
    }
    return g[N] == 0 ? 2 : 1;
  }
}
