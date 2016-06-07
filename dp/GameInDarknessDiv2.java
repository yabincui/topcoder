public class GameInDarknessDiv2 {
  public String check(String[] field, String[] moves) {
    int m = field.length;
    int n = field[0].length();
    StringBuilder builder = new StringBuilder();
    for (String move : moves) {
      builder.append(move);
    }
    String M = builder.toString();

    boolean[][] map1 = new boolean[m][n];
    int ax = 0, ay = 0, bx = 0, by = 0;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (field[i].charAt(j) == 'A') {
          ay = i;
          ax = j;
        } else if (field[i].charAt(j) == 'B') {
          by = i;
          bx = j;
        }
      }
    }
    map1[by][bx] = true;
    boolean aWin = false;
    for (int k = 0; k < M.length() && !aWin; ++k) {
      // A moves.
      if (M.charAt(k) == 'U') {
        ay--;
      } else if (M.charAt(k) == 'D') {
        ay++;
      } else if (M.charAt(k) == 'L') {
        ax--;
      } else if (M.charAt(k) == 'R') {
        ax++;
      }
      map1[ay][ax] = false;
      // B moves.
      boolean[][] map2 = new boolean[m][n];
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (map1[i][j]) {
            if (i > 0 && field[i - 1].charAt(j) != '#') {
              map2[i - 1][j] = true;
            }
            if (i < m - 1 && field[i + 1].charAt(j) != '#') {
              map2[i + 1][j] = true;
            }
            if (j > 0 && field[i].charAt(j - 1) != '#') {
              map2[i][j - 1] = true;
            }
            if (j < n - 1 && field[i].charAt(j + 1) != '#') {
              map2[i][j + 1] = true;
            }
          }
        }
      }
      map2[ay][ax] = false;
      boolean hasValid = false;
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (map2[i][j]) {
            hasValid = true;
          }
        }
      }
      if (!hasValid) {
        aWin = true;
      }
      map1 = map2;
    }
    return aWin ? "Alice wins" : "Bob wins";
  }
}
