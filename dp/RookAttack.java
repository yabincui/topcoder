import java.util.*;

public class RookAttack {
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
    
    private int findNeighbor(Node toNode) {
      for (int i = 0; i < neighbors.size(); ++i) {
        if (neighbors.get(i).toNode == toNode) {
          return i;
        }
      }
      return -1;
    }

    void addEdge(int width, Node toNode) {
      int index = findNeighbor(toNode);
      if (index != -1) {
        neighbors.get(index).width += width;
      } else {
        neighbors.add(new Edge(width, toNode));
      }
    }

    void subEdge(int width, Node toNode) {
      int index = findNeighbor(toNode);
      if (index != -1) {
        int newWidth = neighbors.get(index).width - width;
        if (newWidth <= 0) {
          neighbors.remove(index);
        } else {
          neighbors.get(index).width = newWidth;
        }
      }
    }
  }

  public int howMany(int rows, int cols, String[] cutouts) {
    // Use n rows as in nodes, Use n cols as out nodes, and use chess point as edges.
    Node[] rowNodes = new Node[rows];
    Node[] colNodes = new Node[cols];
    for (int i = 0; i < rows; ++i) {
      rowNodes[i] = new Node();
    }
    for (int i = 0; i < cols; ++i) {
      colNodes[i] = new Node();
    }
    Node source = new Node();
    Node sink = new Node();
    for (int i = 0; i < rows; ++i) {
      source.addEdge(1, rowNodes[i]);
    }
    for (int i = 0; i < cols; ++i) {
      colNodes[i].addEdge(1, sink);
    }
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        rowNodes[i].addEdge(1, colNodes[j]);
      }
    }
    for (String s : cutouts) {
      String[] strs = s.split(" ");
      int r = Integer.parseInt(strs[0]);
      int c = Integer.parseInt(strs[1]);
      rowNodes[r].subEdge(1, colNodes[c]);
    }
    return maxFlow(source, sink);
  }

  class BFSNode {
    BFSNode prev;
    int width;
    Node cur;

    BFSNode(BFSNode prev, int width, Node cur) {
      this.prev = prev;
      this.width = width;
      this.cur = cur;
    }
  }

  int maxFlow(Node source, Node sink) {
    int result = 0;
    while (true) {
      // Use BFS to search possible path from source to sink.
      HashSet<Node> hitSet = new HashSet<Node>();
      Queue<BFSNode> queue = new ArrayDeque<BFSNode>();
      BFSNode bfsNode = new BFSNode(null, Integer.MAX_VALUE, source);
      queue.add(bfsNode);
      hitSet.add(source);
      boolean found = false;
      while (!queue.isEmpty()) {
        bfsNode = queue.poll();
        Node curr = bfsNode.cur;
        for (Edge edge : curr.neighbors) {
          if (edge.toNode == sink) {
            bfsNode = new BFSNode(bfsNode, edge.width, sink);
            found = true;
            break;
          }
          if (!hitSet.contains(edge.toNode)) {
            hitSet.add(edge.toNode);
            queue.add(new BFSNode(bfsNode, edge.width, edge.toNode));
          }
        }
        if (found) {
          break;
        }
      }
      if (!found) {
        break;
      }
      BFSNode last = bfsNode;
      int minWidth = Integer.MAX_VALUE;
      while (bfsNode.cur != source) {
        minWidth = Math.min(minWidth, bfsNode.width);
        bfsNode = bfsNode.prev;
      }
      result += minWidth;
      for (bfsNode = last; bfsNode.cur != source; bfsNode = bfsNode.prev) {
         Node from = bfsNode.prev.cur;
         Node to = bfsNode.cur;
         from.subEdge(minWidth, to);
         to.addEdge(minWidth, from); 
      }
    }
    return result;
  }
}















