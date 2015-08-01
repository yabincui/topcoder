public class TeamBuilder {
  public int[] specialLocations(String[] paths) {
    int n = paths.length;
    boolean[][] reachable = new boolean[n][n];
    for (int i = 0; i < n; ++i) {
      reachable[i][i] = true;
      for (int j = 0; j < n; ++j) {
        if (paths[i].charAt(j) == '1') {
          reachable[i][j] = true;
        }
      }
    }
    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (reachable[i][k] && reachable[k][j]) {
            reachable[i][j] = true;
          }
        }
      }
    }

    int[] result = new int[2];
    for (int i = 0; i < n; ++i) {
      boolean toAll = true;
      for (int j = 0; j < n; ++j) {
        if (!reachable[i][j]) {
          toAll = false;
          break;
        }
      }
      if (toAll) {
        result[0]++;
      }
    }
    for (int i = 0; i < n; ++i) {
      boolean fromAll = true;
      for (int j = 0; j < n; ++j) {
        if (!reachable[j][i]) {
          fromAll = false;
          break;
        }
      }
      if (fromAll) {
        result[1]++;
      }
    }
    return result;
  }
}
