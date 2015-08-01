import java.util.*;

public class BombMan {
  class Node {
    int r;
    int c;
    int bomb;
    int time;

    Node(int r, int c, int bomb, int time) {
      this.r = r;
      this.c = c;
      this.bomb = bomb;
      this.time = time;
    }
  }

  class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
      if (n1.time != n2.time) {
        return n1.time - n2.time;
      }
      if (n1.bomb != n2.bomb) {
        return n1.bomb - n2.bomb;
      }
      if (n1.c != n2.c) {
        return n1.c - n2.c;
      }
      return n1.r - n2.r;
    }
  }

  public int shortestPath(String[] maze, int bombs) {
    int rows = maze.length;
    int cols = maze[0].length();
    int startRow = -1;
    int startCol = -1;
    int endRow = -1;
    int endCol = -1;
    for (int r = 0; r < rows; ++r) {
      for (int c = 0; c < cols; ++c) {
        if (maze[r].charAt(c) == 'B') {
          startRow = r;
          startCol = c;
        } else if (maze[r].charAt(c) == 'E') {
          endRow = r;
          endCol = c;
        }
      }
    }
    boolean[][][] visited = new boolean[rows][cols][bombs + 1];
    PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new NodeComparator());
    Node node = new Node(startRow, startCol, bombs, 0);
    queue.add(node);
    boolean found = false;
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0,  0, -1, 1};
    while (!queue.isEmpty()) {
      node = queue.poll();
      if (visited[node.r][node.c][node.bomb]) {
        continue;
      }
      visited[node.r][node.c][node.bomb] = true;
      if (node.r == endRow && node.c == endCol) {
        found = true;
        break;
      }
      for (int i = 0; i < dr.length; ++i) {
        int nr = node.r + dr[i];
        int nc = node.c + dc[i];
        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
          int nBomb = node.bomb;
          int nTime = node.time + 1;
          if (maze[nr].charAt(nc) == '#') {
            if (node.bomb == 0) {
              continue;
            }
            nBomb = node.bomb - 1;
            nTime = node.time + 3;
          }
          queue.add(new Node(nr, nc, nBomb, nTime));
        }
      }
    }
    return (found ? node.time : -1);
  }
}
