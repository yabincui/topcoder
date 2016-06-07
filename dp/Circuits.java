public class Circuits {
  public int howLong(String[] connects, String[] costs) {
    int n = connects.length;
    int[][] neighbor = new int[n][n];
    for (int i = 0; i < n; ++i) {
      String[] nodes = connects[i].split(" ");
      String[] dists = costs[i].split(" ");
      for (int j = 0; j < nodes.length; ++j) {
        if (nodes[j].length() == 0) {
          continue;
        }
        int node = Integer.parseInt(nodes[j]);
        int dist = Integer.parseInt(dists[j]);
        neighbor[i][node] = dist;
      }
    }
    int maxDist = 0;
    for (int i = 0; i < n; ++i) {
      int[] dists = new int[n];
      dfsSearch(i, 0, neighbor, dists);
      for (int j = 0; j < n; ++j) {
        maxDist = Math.max(maxDist, dists[j]);
      }
    }
    flood(neighbor);
    int maxDist2 = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        maxDist2 = Math.max(maxDist2, neighbor[i][j]);
      }
    }
    if (maxDist != maxDist2) {
      return -1;
    }
    return maxDist;
  }

  void dfsSearch(int currNode, int currDist, int[][] neighbor, int[] dists) {
    int n = dists.length;
    for (int i = 0; i < n; ++i) {
      if (neighbor[currNode][i] != 0) {
        int newDist = currDist + neighbor[currNode][i];
        if (newDist > dists[i]) {
          dists[i] = newDist;
          dfsSearch(i, dists[i], neighbor, dists);
        }
      }
    }
  }

  // Use it seems better.
  void flood(int[][] neighbor) {
    int n = neighbor.length;
    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (neighbor[i][k] != 0 && neighbor[k][j] != 0) {
            neighbor[i][j] = Math.max(neighbor[i][j], neighbor[i][k] + neighbor[k][j]);
          }
        }
      }
    }
  }
}
