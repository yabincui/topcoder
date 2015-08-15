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

    int minSteps = findMinStepByMaxFlow2(sources, targets, dist);

    int[] selectArray = new int[sources.length];
    boolean[] choosen = new boolean[sources.length];
    int minStep2 = permute(0, sources, targets, selectArray, choosen, dist);

    System.out.printf("minStep = %d, minStep2 = %d, result = %d\n", minSteps, 
        minStep2, result + minSteps);
    result += minSteps;
    
    return result;
  }

  // Method to verify result.
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
  
  class Edge {
    int width;
    Node toNode;
    
    Edge(int width, Node toNode) {
      this.width = width;
      this.toNode = toNode;
    }
  }
  
  class Node {
    ArrayList<Edge> neighbors = new ArrayList<Edge>();
  
    Edge findEdge(Node toNode) {
      for (Edge edge : neighbors) {
        if (edge.toNode == toNode) {
          return edge;
        }
      }
      return null;
    }
    
    void addEdge(int width, Node toNode) {
      Edge edge = findEdge(toNode);
      if (edge != null) {
        edge.width += width;
      } else {
        neighbors.add(new Edge(width, toNode));
      }
    }
    
    void subEdge(int width, Node toNode) {
      Edge edge = findEdge(toNode);
      if (edge != null) {
        edge.width -= width;
        if (edge.width == 0) {
          neighbors.remove(edge);
        }
      }
    }
  }
  
  class Connect {
    int width;
    int dist;
    
    Connect(int width, int dist) {
      this.width = width;
      this.dist = dist;
    }
  }
  
  // Change the way to find path. Don't use bfs/dfs, use single source shortest
  // path with negative edge.
  int findMinStepByMaxFlow2(int[] A, int[] B, int[][] boardDist) {
    int n = A.length;
    int size = n * 2 + 2;
    Connect[][] m = new Connect[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        m[i][j] = new Connect(0, 0);
      }
    }
    for (int i = 0; i < n; ++i) {
      for (int j = n; j < 2 * n; ++j) {
        m[i][j] = new Connect(1, boardDist[A[i]][B[j - n]]);
        m[j][i] = new Connect(0, -boardDist[A[i]][B[j - n]]);
      }
    }
    int source = n * 2;
    int sink = n * 2 + 1;
    for (int i = 0; i < n; ++i) {
      m[source][i] = new Connect(1, 0);
    }
    for (int j = n; j < 2 * n; ++j) {
      m[j][sink] = new Connect(1, 0);
    }
    
    int lastWidth = n;
    int distSum = 0;
    while (lastWidth > 0) {
      int[] prev = new int[size];
      int[] dist = new int[size];
      for (int i = 0; i < size; ++i) {
        dist[i] = -1;
      }
      dist[source] = 0;
      PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
      queue.add((0 << 16) | source);
      // We may have negative dist, so loop until the queue becomes empty.
      while (!queue.isEmpty()) {
        int value = queue.poll();
        int curDist = value >> 16;
        int curNode = value & ((1 << 16) - 1);
        if (curDist > dist[curNode]) {
          continue;
        }
        for (int nextNode = 0; nextNode < size; ++nextNode) {
          if (m[curNode][nextNode].width > 0 && (
              dist[nextNode] == -1 || dist[nextNode] > dist[curNode] + m[curNode][nextNode].dist)) {
            dist[nextNode] = dist[curNode] + m[curNode][nextNode].dist;
            prev[nextNode] = curNode;
            queue.add((dist[nextNode] << 16) | nextNode);
          }
        }
      }
      if (dist[sink] == -1) {
        break;
      }
      lastWidth--;
      for (int cur = sink; cur != source; cur = prev[cur]) {
        int curNode = cur;
        int prevNode = prev[cur];
        m[prevNode][curNode].width--;
        m[curNode][prevNode].width++;
      }
      distSum += dist[sink];
    }
    return distSum;
  }
  
  // Wrong answer, maximum flow using bfs to search path is not enough.
  int findMinStepByMaxFlow(int[] A, int[] B, int[][] dist) {
    Node[] from = new Node[A.length];
    for (int i = 0; i < from.length; ++i) {
      from[i] = new Node();
    }
    Node[] to = new Node[B.length];
    for (int i = 0; i < to.length; ++i) {
      to[i] = new Node();
    }
    Node source = new Node();
    Node sink = new Node();
    /*
    int maxDist = 0;
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < B.length; ++j) {
        int d = dist[A[i]][B[j]];
        maxDist = Math.max(maxDist, d);
      }
    }
    */
    int standardWidth = 100;
    System.out.printf("standardWidth = %d\n", standardWidth);
    for (int i = 0; i < A.length; ++i) {
      source.addEdge(standardWidth * 2, from[i]);
    }
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < B.length; ++j) {
        System.out.printf("width[i][j] = %d\n", standardWidth * 2 - dist[A[i]][B[j]]);
        from[i].addEdge(standardWidth * 2 - dist[A[i]][B[j]], to[j]);
      }
    }
    for (int j = 0; j < B.length; ++j) {
      to[j].addEdge(standardWidth * 2, sink);
    }
    int maxFlow = findMaxFlow(source, sink, standardWidth);
    int minStep = (2 * standardWidth * from.length - maxFlow);
    System.out.printf("maxFlow = %d, from.length = %d, standardWidth = %d, minStep = %d\n",
        maxFlow, from.length, standardWidth, minStep);
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < B.length; ++j) {
        Edge edge = to[j].findEdge(from[i]);
        if (edge != null) {
          System.out.printf("edge from[%d] -> to[%d], width %d, dist %d, dist[%d][%d] = %d\n",
              i, j, edge.width, 2 * standardWidth - edge.width, i, j, dist[A[i]][B[j]]);
        }
      }
    }
    
    return minStep;
  }
  
  class PathSegment {
    PathSegment prev;
    int width;
    Node cur;
    
    PathSegment(PathSegment prev, int width, Node cur) {
      this.prev = prev;
      this.width = width;
      this.cur = cur;
    }
  }
  
  int findMaxFlow(Node source, Node sink, int standardWidth) {
    int result = 0;
    while (true) {
      Queue<PathSegment> queue = new ArrayDeque<PathSegment>();
      HashSet<Node> hitSet = new HashSet<Node>();
      queue.add(new PathSegment(null, 0, source));
      hitSet.add(source);
      boolean found = false;
      PathSegment foundSegment = null;
      while (!queue.isEmpty()) {
        PathSegment seg = queue.poll();
        for (Edge edge : seg.cur.neighbors) {
          if (edge.toNode == sink) {
            foundSegment = new PathSegment(seg, edge.width, sink);
            found = true;
            break;
          }
          if (!hitSet.contains(edge.toNode)) {
            hitSet.add(edge.toNode);
            queue.add(new PathSegment(seg, edge.width, edge.toNode));
          }
        }
        if (found) {
          break;
        }
      }
      if (!found) {
        break;
      }
      int minWidth = Integer.MAX_VALUE;
      for (PathSegment seg = foundSegment; seg.prev != null; seg = seg.prev) {
        minWidth = Math.min(minWidth, seg.width);
      }
      System.out.printf("minWidth = %d\n", minWidth);
      result += minWidth;
      for (PathSegment seg = foundSegment; seg.prev != null; seg = seg.prev) {
        Node from = seg.prev.cur;
        Node to = seg.cur;
        from.subEdge(minWidth, to);
        to.addEdge(minWidth, from);
      }
    }
    return result;
  }
}
