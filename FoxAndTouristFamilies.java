import java.util.*;

public class FoxAndTouristFamilies {
  class Node {
    ArrayList<Integer> s = new ArrayList<Integer>();
  }

  public double expectedLength(int[] A, int[] B, int[] f) {
    int n = A.length + 1;
    Node[][] paths = new Node[n][n];
    for (int i = 0; i < A.length; ++i) {
      Node path = new Node();
      path.s.add(i);
      paths[A[i]][B[i]] = path;
      paths[B[i]][A[i]] = path;
    }
    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          if (paths[i][j] == null && paths[i][k] != null && paths[k][j] != null) {
            Node path = new Node();
            path.s.addAll(paths[i][k].s);
            path.s.addAll(paths[k][j].s);
            paths[i][j] = paths[j][i] = path;
          }
        }
      }
    }

    // possibility[i][j] is the possibility of family at city i access road j.
    double[][] possibility = new double[n][n - 1];
    double p = 1.0 / (n - 1);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (j == i) {
          continue;
        }
        double temp = 1.0 / (n - 1);
        for (int k = 0; k < paths[i][j].s.size(); ++k) {
          int road = paths[i][j].s.get(k);
          possibility[i][road] += p;
        }
      }
    }
    double result = 0;
    for (int i = 0; i < n - 1; ++i) {
      double pro = 1.0;
      for (int j = 0; j < f.length; ++j) {
        int city = f[j];
        double temp = possibility[city][i];
        pro *= temp;
      }
      result += pro;
    }
    return result;
  }
}
