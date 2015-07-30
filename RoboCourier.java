import java.util.*;

public class RoboCourier {
  // Angle   0
  //       5   1
  //       4   2
  //         3

  class Node {
    int index;
    double x;
    double y;
    int[] neighbor;

    Node(int index, double x, double y) {
      this.index = index;
      this.x = x;
      this.y = y;
      this.neighbor = new int[6];
      for (int i = 0; i < 6; ++i) {
        this.neighbor[i] = -1;
      }
    }

    boolean equals(double x, double y) {
      if (Math.abs(this.x - x) < 1e-6 && Math.abs(this.y - y) < 1e-6) {
        return true;
      }
      return false;
    }

    void addNeighbor(Node node, int angle) {
      neighbor[angle] = node.index;
      node.neighbor[(angle + 3) % 6] = index;
    }
  }

  public int timeToDeliver(String[] path) {
    int[] targetNode = new int[1];
    ArrayList<Node> nodes = pathToNodes(path, targetNode);
    int n = nodes.size() * 6;
    int[][] map = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        map[i][j] = -1;
      }
    }
    for (int i = 0; i < n; ++i) {
      if (i % 6 != 5) {
        map[i][i + 1] = map[i + 1][i] = 3;
      } else {
        map[i][i - 5] = map[i - 5][i] = 3;
      }
    }
    for (int i = 0; i < nodes.size(); ++i) {
      Node node = nodes.get(i);
      for (int j = 0; j < 6; ++j) {
        Node currNode = node;
        int dist = 0;
        while (currNode.neighbor[j] != -1) {
          dist++;
          currNode = nodes.get(currNode.neighbor[j]);
          if (dist <= 2) {
            map[node.index * 6 + j][currNode.index * 6 + j] = dist * 4;
          } else {
            map[node.index * 6 + j][currNode.index * 6 + j] = (dist - 2) * 2 + 8;
          }
        }
      }
    }
    int[] costFromZero = new int[n];
    for (int i = 0; i < n; ++i) {
      costFromZero[i] = Integer.MAX_VALUE;
    }
    costFromZero[0] = 0;
    boolean[] visited = new boolean[n];
    while (true) {
      int minIndex = -1;
      for (int i = 0; i < n; ++i) {
        if (!visited[i] && (minIndex == -1 || costFromZero[i] < costFromZero[minIndex])) {
          minIndex = i;
        }
      }
      if (minIndex == -1 ||
        (minIndex >= (targetNode[0] * 6) && (minIndex < (targetNode[0] + 1) * 6))) {
        break;
      }
      visited[minIndex] = true;
      for (int i = 0; i < n; ++i) {
        if (map[minIndex][i] != -1 && !visited[i]) {
          costFromZero[i] = Math.min(costFromZero[i], costFromZero[minIndex] + map[minIndex][i]);
        }
      }
    }
    int result = Integer.MAX_VALUE;
    for (int i = targetNode[0] * 6; i < (targetNode[0] + 1) * 6; ++i) {
      result = Math.min(result, costFromZero[i]);
    }
    return result;
  }

  ArrayList<Node> pathToNodes(String[] path, int[] targetNode) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    StringBuilder builder = new StringBuilder();
    for (String s : path) {
      builder.append(s);
    }
    String steps = builder.toString();
    Node curNode = new Node(0, 0, 0);
    nodes.add(curNode);
    double curX = 0;
    double curY = 0;
    int angle = 0;
    double sin60 = Math.sqrt(3) / 2;
    double[] dx = new double[]{0, sin60, sin60, 0, -sin60, -sin60};
    double[] dy = new double[]{1, 0.5,   -0.5, -1,  -0.5,    0.5};
    for (int i = 0; i < steps.length(); ++i) {
      char c = steps.charAt(i);
      if (c == 'L') {
        angle = (angle + 5) % 6;
      } else if (c == 'R') {
        angle = (angle + 1) % 6;
      } else if (c == 'F') {
        curX += dx[angle];
        curY += dy[angle];
        boolean existed = false;
        for (int j = 0; j < nodes.size(); ++j) {
          if (nodes.get(j).equals(curX, curY)) {
            existed = true;
            curNode.addNeighbor(nodes.get(j), angle);
            curNode = nodes.get(j);
            break;
          }
        }
        if (!existed) {
          Node node = new Node(nodes.size(), curX, curY);
          nodes.add(node);
          curNode.addNeighbor(node, angle);
          curNode = node;
        }
      }
    }
    targetNode[0] = curNode.index;
    return nodes;
  }
}
