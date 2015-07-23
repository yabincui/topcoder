import java.util.*;

public class SolitaireChess {
  public int transform(String[] board1, String[] board2) {
    char[][] b1 = new char[board1.length][board1[0].length()];
    char[][] b2 = new char[board1.length][board1[0].length()];
    for (int i = 0; i < board1.length; ++i) {
      for (int j = 0; j < board1[0].length(); ++j) {
        b1[i][j] = board1[i].charAt(j);
        b2[i][j] = board2[i].charAt(j);
      }
    }

    if (getCount(b1) != getCount(b2)) {
      return -1;
    }
    int result = matchP(b1, b2);
    if (result == -1) {
      return -1;
    }
    // pos (r = pos /8, c = pos % 8)
    int[] sources = buildNodes(b1);
    int[] targets = buildNodes(b2);
    System.out.printf("basic step = %d\n", result);
    System.out.printf("sources[] =");
    for (int i = 0; i < sources.length; ++i) {
      System.out.printf(" %d", sources[i]);
    }
    System.out.printf("\n");
    System.out.printf("targets[] =");
    for (int i = 0; i < targets.length; ++i) {
      System.out.printf(" %d", targets[i]);
    }
    System.out.printf("\n");
    
    int[][] pair = removeEqual(sources, targets);
    sources = pair[0];
    targets = pair[1];
    
    System.out.printf("basic step = %d\n", result);
    System.out.printf("sources[] =");
    for (int i = 0; i < sources.length; ++i) {
      System.out.printf(" %d", sources[i]);
    }
    System.out.printf("\n");
    System.out.printf("targets[] =");
    for (int i = 0; i < targets.length; ++i) {
      System.out.printf(" %d", targets[i]);
    }
    System.out.printf("\n");
    

    int[][] dist = calculateDist(b1.length, b1[0].length);
    if (sources.length != targets.length) {
      return -1;
    }

    // source[i] select target[selects[i]].
    int[] selects = new int[sources.length];
    // Init selection.
    for (int i = 0; i < targets.length; ++i) {
      selects[i] = i;
    }

    int minSteps = 0;
    for (int i = 0; i < sources.length; ++i) {
      int from = sources[i];
      int to = targets[selects[i]];
      minSteps += dist[from][to];
    }
    while (true) {
      boolean findSwap = false;
      // try swap.
      for (int i = 0; i < sources.length; ++i) {
        for (int j = i + 1; j < sources.length; ++j) {
          int from1 = sources[i];
          int to1 = targets[selects[i]];
          int from2 = sources[j];
          int to2 = targets[selects[j]];
          int originSteps = dist[from1][to1] + dist[from2][to2];
          int newSteps = dist[from1][to2] + dist[from2][to1];
          if (newSteps < originSteps) {
            findSwap = true;
            int temp = selects[i];
            selects[i] = selects[j];
            selects[j] = temp;
            minSteps -= originSteps - newSteps;
            break;
          }
        }
      }
      
      if (!findSwap) {
        break;
      }
    }

    int[] selectArray = new int[sources.length];
    boolean[] choosen = new boolean[sources.length];
    int minStep2 = permute(0, sources, targets, selectArray, choosen, dist);

    System.out.printf("minStep = %d, minStep2 = %d, result = %d\n", minSteps, 
        minStep2, result + minSteps);
    result += minSteps;
    
    return result;
  }

  int permute(int k, int[] sources, int[] targets, int[] select, boolean[] choosen, int[][] dist) {
    if (k == sources.length) {
      int steps = 0;
      for (int i = 0; i < sources.length; ++i) {
        int from = sources[i];
        int to = targets[select[i]];
        steps += dist[from][to];
      }
      return steps;
    }
    int minStep = Integer.MAX_VALUE;
    for (int i = 0; i < choosen.length; ++i) {
      if (choosen[i]) {
        continue;
      }
      select[k] = i;
      choosen[i] = true;
      int step = permute(k + 1, sources, targets, select, choosen, dist);
      minStep = Math.min(step, minStep);
      choosen[i] = false;
    }
    return minStep;
  }

  int getCount(char[][] board) {
    int count = 0;
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if (board[i][j] != '.') {
          ++count;
        }
      }
    }
    return count;
  }

  int matchP(char[][] board1, char[][] board2) {
    int steps = 0;
    for (int c = 0; c < board1[0].length; ++c) {
      for (int r = board1.length - 1; r > 0; --r) {
        if (board2[r][c] == 'P') {
          boolean find_match = false;
          for (int r1 = board1.length - 1; r1 >= r; --r1) {
            if (board1[r1][c] == 'P') {
              find_match = true;
              steps += r1 - r;
              board1[r1][c] = '.';
              board2[r][c] = '.';
              break;
            }
          }
          if (!find_match) {
            return -1;
          }
        }
      }
    }
    for (int r = 0; r < board1.length; ++r) {
      for (int c = 0; c < board1[0].length; ++c) {
        if (board1[r][c] == 'P') {
          steps += r;
        }
      }
    }
    return steps;
  }

  int[] buildNodes(char[][] board) {
    ArrayList<Integer> nodes = new ArrayList<Integer>();
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        if (board[i][j] == 'N') {
          nodes.add(i * board[0].length + j);
        } else if (board[i][j] == 'P') {
          nodes.add(j);
        }
      }
    }
    return toArray(nodes);
  }

  int[] toArray(ArrayList<Integer> list) {
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); ++i) {
      result[i] = list.get(i);
    }
    return result;
  }

  int[][] calculateDist(int rows, int cols) {
    int n = rows * cols;
    int[][] dist = new int[n][n];

    int[] dr = new int[]{-1, -2, -2, -1,  1,  2, 2, 1};
    int[] dc = new int[]{-2, -1,  1,  2, -2, -1, 1, 2};

    for (int i = 0; i < n; ++i) {
      int r = i / cols;
      int c = i % cols;
      for (int t = 0; t < 8; ++t) {
        int nr = r + dr[t];
        int nc = c + dc[t];
        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
          int ni = nr * cols + nc;
          dist[i][ni] = 1;
        }
      }
    }

    // Using flood algorithm.
    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (i == j || i == k || j == k) {
            continue;
          }
          if (dist[i][k] != 0 && dist[k][j] != 0) {
            if (dist[i][j] == 0 || dist[i][j] > dist[i][k] + dist[k][j]) {
              dist[i][j] = dist[i][k] + dist[k][j];
            }
          }
        }
      }
    }
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (i != j && dist[i][j] == 0) {
          // check for error.
          return null;
        }
      }
    }
    return dist;
  }

  int[][] removeEqual(int[] s1, int[] s2) {
    Arrays.sort(s1);
    Arrays.sort(s2);
    ArrayList<Integer> array1 = new ArrayList<Integer>();
    ArrayList<Integer> array2 = new ArrayList<Integer>();
    int i = 0;
    int j = 0;
    while (i < s1.length && j < s2.length) {
      if (s1[i] < s2[j]) {
        array1.add(s1[i]);
        ++i;
      } else if (s1[i] > s2[j]) {
        array2.add(s2[j]);
        ++j;
      } else {
        ++i; ++j;
      }
    }
    while (i < s1.length) {
      array1.add(s1[i++]);
    }
    while (j < s2.length) {
      array2.add(s2[j++]);
    }
    int[][] result = new int[2][];
    result[0] = toArray(array1);
    result[1] = toArray(array2);
    return result;
  }
}
