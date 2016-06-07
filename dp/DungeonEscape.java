import java.util.*;

public class DungeonEscape {
  class Node {
    int r;
    int c;
    int time;
    
    Node(int r, int c, int time) {
      this.r = r;
      this.c = c;
      this.time = time;
    }
  }

  class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
      if (n1.time != n2.time) {
        return n1.time - n2.time;
      }
      if (n1.c != n2.c) {
        return n1.c - n2.c;
      }
      return n1.r - n2.r;
    }
  }

  public int exitTime(String[] up, String[] down, String[] east, String[] west, int startLevel,
                      int startEasting) {
    int rows = up.length;
    int cols = up[0].length();
    int times = rows * cols;
    boolean[][] visited = new boolean[rows][cols];
    PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new NodeComparator());
    Node node = new Node(startLevel, startEasting, 0);
    queue.add(node);
    boolean escaped = false;
    while (!queue.isEmpty()) {
      node = queue.poll();
      if (node.r == -1) {
        escaped = true;
        break;
      }
      if (visited[node.r][node.c]) {
        continue;
      }
      visited[node.r][node.c] = true;
      // Try up.
      char letter = up[node.r].charAt(node.c);
      if (letter != 'x') {
        if (canGo(node.r - 1, node.time + letter - '0', rows, cols)) {
          Node next = new Node(node.r - 1, node.c, node.time + letter - '0');
          queue.add(next);
        }
      }
      // Try down.
      if (node.r < rows - 1) {
        letter = down[node.r].charAt(node.c);
        if (letter != 'x') {
          if (canGo(node.r + 1, node.time + letter - '0', rows, cols)) {
            Node next = new Node(node.r + 1, node.c, node.time + letter - '0');
            queue.add(next);
          }
        }
      }
      // Try east.
      if (node.c < cols - 1) {
        letter = east[node.r].charAt(node.c);
        if (letter != 'x') {
          if (canGo(node.r, node.time + letter - '0', rows, cols)) {
            Node next = new Node(node.r, node.c + 1, node.time + letter - '0');
            queue.add(next);
          }
        }
      }
      // Try west.
      if (node.c > 0) {
        letter = west[node.r].charAt(node.c);
        if (letter != 'x') {
          if (canGo(node.r, node.time + letter - '0', rows, cols)) {
            Node next = new Node(node.r, node.c - 1, node.time + letter - '0');
            queue.add(next);
          }
        }
      }
    }
    return (escaped ? node.time : -1);
  }

  boolean canGo(int r, int time, int rows, int cols) {
    int coverRows = time / cols;
    int unableRow = rows - coverRows;
    if (r >= unableRow) {
      return false;
    }
    return true;
  }
}
